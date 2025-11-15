package com.fernando.springboot.shop.api.shop.common.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;


@Configuration
public class CloudinaryConfig {
    
    @Value("${cloudinary.name}")
    private String cloudinaryName;

    @Value("${cloudinary.api_key}")
    private String cloudinaryApiKey;

    @Value("${cloudinary.api_secret}")
    private String cloudinaryApiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
            "cloud_name", cloudinaryName,
            "api_key", cloudinaryApiKey,
            "api_secret", cloudinaryApiSecret
        ));
    }
}
