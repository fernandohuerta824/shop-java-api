package com.fernando.springboot.shop.api.shop.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
