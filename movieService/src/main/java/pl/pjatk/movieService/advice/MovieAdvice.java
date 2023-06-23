package pl.pjatk.movieService.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class MovieAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeExceptionHandler(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exception has been throwed: " + ex.getLocalizedMessage());
    }
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> badRequestExceptionHandler(HttpClientErrorException.BadRequest ex) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
