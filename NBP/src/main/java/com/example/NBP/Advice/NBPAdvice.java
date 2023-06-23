package com.example.NBP.Advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class NBPAdvice {
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientError(HttpClientErrorException ex) {
        return ResponseEntity
                .status(ex.getStatusCode()) // Używamy tego samego kodu statusu, który otrzymaliśmy od serwera NBP
                .body("Wystąpił problem podczas odpytywania API NBP: " + ex.getMessage());
    }
}
