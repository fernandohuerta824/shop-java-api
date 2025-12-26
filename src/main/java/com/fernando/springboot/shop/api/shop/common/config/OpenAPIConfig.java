package com.fernando.springboot.shop.api.shop.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de una tienda online")
                .version("1.0.0")
            );
    }

    @Bean
    public GroupedOpenApi v1Group() {
        return GroupedOpenApi
            .builder()
            .group("v1")
            .pathsToMatch("/api/v1/**")
            .build();
    }

    @Bean
    public GroupedOpenApi v2Group() {
        return GroupedOpenApi
            .builder()
            .group("v2")
            .pathsToMatch("/api/v2/**")
            .build();
    }

    @Bean
    public GroupedOpenApi authGroup() {
        return GroupedOpenApi
            .builder()
            .group("Auth")
            .pathsToMatch("/auth/**")
            .build();
    }
}
