package com.example.putdefault.api.controller;

import com.example.putdefault.api.controller.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class ErrorAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        LOG.warn("Not found resource with ID : {}", ex.getIdNotfound());
        return ResponseEntity.notFound()
                .build();
    }

}
