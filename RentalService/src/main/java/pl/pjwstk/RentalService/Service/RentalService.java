package pl.pjwstk.RentalService.Service;

import org.springframework.http.*;
import org.springframework.web.client.*;
import pl.pjwstk.RentalService.Model.Movie;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.net.URI;

@Service
public class RentalService {
    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(Long id) {
        return restTemplate.getForObject("http://localhost:8080/movieService/movies/"+ id,Movie.class);
    }

    public ResponseEntity<Void> returnMovie(Long id) {
        try {
            final String uri = "http://localhost:8080/movieService/movies/" + id + "/available";

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.PUT,
                    entity,
                    String.class);

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.badRequest().build();
            } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            } else {
                return ResponseEntity.ok().build();
            }
        } catch (RestClientException e) {
            if (e.getCause() instanceof ConnectException) {
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
            } else {
                throw e;
            }
        }
    }

    public ResponseEntity<Void> rentMovie(Long id) {
        try {
            final String uri = "http://localhost:8080/movieService/movies/" + id + "/unavailable";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.PUT,
                    entity,
                    String.class
            );
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new HttpClientErrorException(response.getStatusCode());
            }

            return ResponseEntity.ok().build();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) { // 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) { // 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) { // 500
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build(); // Zwraca 502
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (RestClientException e) {
            if (e.getCause() instanceof ConnectException) {
                return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build(); // Zwraca 504
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
