package com.fernando.springboot.shop.api.shop.modules.handlerException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fernando.springboot.shop.api.shop.domain.exception.BussinesException;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceAlreadyExistsException;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.domain.exception.UnauthorizatedException;
import com.fernando.springboot.shop.api.shop.domain.response.ApiResponse;
import com.fernando.springboot.shop.api.shop.domain.response.BuildResponse;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler({UnauthorizatedException.class}) 
    public ResponseEntity<ApiResponse<String>> unauthorizatedException(Exception ex) {
        return BuildResponse.build("Error al iniciar sesion", HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
    
    @ExceptionHandler({ResourceNotFoundException.class, NoResourceFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ApiResponse<String>> handleResourcenotFound(Exception ex) {
        return BuildResponse.build("Recurso no encontrado", HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<ApiResponse<String>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        return BuildResponse.build("El recurso ya existe", HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({BussinesException.class})
    public ResponseEntity<ApiResponse<String>> handleBussinesException(BussinesException ex) {
        return BuildResponse.build("Error de negocios", HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(err -> {
                    errors.put(err.getField(), err.getDefaultMessage());
                });

        return BuildResponse.build(
            "Error de validacion", 
            HttpStatus.UNPROCESSABLE_ENTITY, 
            errors
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidJson(HttpMessageNotReadableException ex) {
        return BuildResponse.build(
            "JSON inválido",
            HttpStatus.BAD_REQUEST,
            "El cuerpo de la petición no es válido"
        );
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidArgumentType(MethodArgumentTypeMismatchException ex) {
        return BuildResponse.build(
            "Parámetro inválido",
            HttpStatus.BAD_REQUEST,
            "El parámetro '" + ex.getName() + "' tiene un tipo inválido"
        );
    }

     @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingParam(MissingServletRequestParameterException ex) {
        return BuildResponse.build(
            "Parámetro faltante",
            HttpStatus.BAD_REQUEST,
            "Falta el parámetro: " + ex.getParameterName()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return BuildResponse.build(
            "Método no permitido",
            HttpStatus.METHOD_NOT_ALLOWED,
            ex.getMessage()
        );
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> handleMediaNotSupported(HttpMediaTypeNotSupportedException ex) {
        return BuildResponse.build(
            "Tipo de contenido no soportado",
            HttpStatus.UNSUPPORTED_MEDIA_TYPE,
            ex.getMessage()
        );
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponse<String>> exceptin(RuntimeException ex) {
        return BuildResponse.build("Error del servidor", HttpStatus.INTERNAL_SERVER_ERROR, "Algo salio mal, por favor intente mas tarde");
    }

}
