package com.fernando.springboot.shop.api.shop.domain.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fernando.springboot.shop.api.shop.modules.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
public class JwtService {
    
    @Value("${security.jwt.secret}")
    private String secret;

    private SecretKey secretKey = null;

  @PostConstruct
    void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generate(User user) {
        return Jwts.builder()
                .setSubject(user.getCode().toString())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 15)
                )
                .signWith(
                        Keys.hmacShaKeyFor(secret.getBytes())
                )
                .compact();
    }
    
    public boolean isValid(String jwt) {

        try {
            
            parse(jwt);
            return true;
        } catch(JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    


    public String getCode(String token) {
        return parse(token).getSubject();
    }

    public String getRole(String token) {
        return parse(token).get("role", String.class);
    }

    private Claims parse(String jwt) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
