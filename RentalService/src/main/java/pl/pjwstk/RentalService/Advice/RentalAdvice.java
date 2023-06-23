package pl.pjwstk.RentalService.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;

@ControllerAdvice
public class RentalAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
        } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException e) {
        if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Bad gateway");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> handleConnectException(ConnectException e) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Gateway timeout");
    }
}
