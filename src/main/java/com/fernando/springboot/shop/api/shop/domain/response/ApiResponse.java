package com.fernando.springboot.shop.api.shop.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private String  timeStamp;
    private T data;

    public ApiResponse(
        int code,
        String message,
        Instant timeStamp,
        T data
    ) {
        this.code = code;
        this.message = message;
        this.timeStamp = timeStamp.toString();
        this.data = data;
    }
}
