package com.fernando.springboot.shop.api.shop.domain.exception;

public class BussinesException extends RuntimeException {
    
    public BussinesException(String message) {
        super(message);
    }
}
