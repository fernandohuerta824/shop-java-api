package com.fernando.springboot.shop.api.shop.modules.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.springboot.shop.api.shop.domain.response.ApiResponse;
import com.fernando.springboot.shop.api.shop.domain.response.BuildResponse;
import com.fernando.springboot.shop.api.shop.modules.auth.dto.LoggedinDto;
import com.fernando.springboot.shop.api.shop.modules.auth.dto.LoginDto;
import com.fernando.springboot.shop.api.shop.modules.auth.dto.SignupDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup") 
    public ResponseEntity<ApiResponse<Object>> signup(
        @RequestBody @Valid SignupDto dto
    ) {
        
        authService.signup(dto);
        Object o = null;
        return BuildResponse.build(
            "Usuario creado correctamente", 
            HttpStatus.CREATED, 
            o
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoggedinDto>> login(
        @RequestBody LoginDto dto
    ) {
        LoggedinDto info = authService.login(dto);

        return BuildResponse.build(
            "Usuario autenticado correctamente", 
            HttpStatus.OK, 
            info
        );
    }
}
