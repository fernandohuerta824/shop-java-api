package com.fernando.springboot.shop.api.shop.modules.product.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v2/products")
@AllArgsConstructor
@Tag(name = "Products", description = "Maneja los endpoints de la version dos de productos")
public class ProductControllerV2 {
    
    private final ProductServiceV2 productServiceV2;



}
