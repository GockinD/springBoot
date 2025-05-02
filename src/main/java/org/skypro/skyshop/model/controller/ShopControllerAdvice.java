package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<String> noSuchProductException(NoSuchProductException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
