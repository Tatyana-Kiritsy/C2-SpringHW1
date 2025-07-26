package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<ShopError> handleException(NoSuchFieldException e) {
        ShopError shopError = new ShopError("NO_SUCH_PRODUCT_WITH_THIS_ID",
                "Продукт не найден!");
        return new ResponseEntity<ShopError>(shopError, HttpStatus.NOT_FOUND);
    }
}
