package com.fernando.springboot.shop.api.shop.domain.response;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class BuildResponse {
    private BuildResponse () {}

    public static <T> ResponseEntity<ApiResponse<T>> build (
        String message,
        HttpStatus code,
        T data
    ) {
        return ResponseEntity.status(code).body(
            new ApiResponse<T>(
                code.value(),
                message,
                Instant.now(),
                data
            )
        );
    }

    public static <T> ResponseEntity<ApiPageResponse<T>> build (
        String message,
        HttpStatus code,
        Page<T> data
    ) {
        return ResponseEntity.status(code).body(
            new ApiPageResponse<T>(
                message,
                code.value(),
                Instant.now(),
                data
            )
        );
    }
}
