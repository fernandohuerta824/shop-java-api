package com.fernando.springboot.shop.api.shop.modules.handlerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.domain.response.ApiResponse;
import com.fernando.springboot.shop.api.shop.domain.response.BuildResponse;

@RestControllerAdvice
public class GlobalHandlerException {
    
    @ExceptionHandler({ResourceNotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ApiResponse<String>> notFound(RuntimeException ex) {
        return BuildResponse.build("Resource not found", HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponse<String>> exceptin(RuntimeException ex) {
        return BuildResponse.build("Error Server", HttpStatus.INTERNAL_SERVER_ERROR, "Algo salio mal, por favor intente mas tarde");
    }
}
