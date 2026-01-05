package com.fernando.springboot.shop.api.shop.domain.exception;

public class UnauthorizatedException extends RuntimeException{
    
    public UnauthorizatedException(String message) {
        super(message);
    }
}
