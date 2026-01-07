package com.fernando.springboot.shop.api.shop.domain.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.springboot.shop.api.shop.domain.response.ApiResponse;

@Component
@AllArgsConstructor
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
        response.setContentType("application/json");

        ApiResponse<Map<String, String>> res = new ApiResponse<>(
            401, 
            "Se requiere autenticacion para acceder este recurso", 
            Instant.now(), 
            Map.of("path", request.getRequestURI())
        );

        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}