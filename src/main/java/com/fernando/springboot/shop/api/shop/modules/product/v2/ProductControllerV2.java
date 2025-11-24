package com.fernando.springboot.shop.api.shop.modules.product.v2;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.springboot.shop.api.shop.domain.response.ApiResponse;
import com.fernando.springboot.shop.api.shop.domain.response.BuildResponse;
import com.fernando.springboot.shop.api.shop.domain.validation.OnCreate;
import com.fernando.springboot.shop.api.shop.domain.validation.OnUpdate;
import com.fernando.springboot.shop.api.shop.modules.category.Category;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductDto;
import com.fernando.springboot.shop.api.shop.modules.product.v2.dto.ProductBodyDtoV2;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v2/products")
@AllArgsConstructor
@Tag(name = "Products", description = "Maneja los endpoints de la version dos de productos")
public class ProductControllerV2 {
    
    private final ProductServiceV2 productServiceV2;


    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> save(
        @RequestBody @Validated(OnCreate.class) ProductBodyDtoV2 body 
    ) {
        ProductDto product = productServiceV2.save(body);

        return BuildResponse.build(
            "Producto guardado correctamente", 
            HttpStatus.CREATED, 
            product
        );
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ApiResponse<ProductDto>> update(
        @RequestBody @Validated(OnUpdate.class) ProductBodyDtoV2 body ,
        @PathVariable String code
    ) {
        ProductDto product = productServiceV2.update(code, body);

        return BuildResponse.build(
            "Producto actualizado correctamente", 
            HttpStatus.OK, 
            product
        );
    }
}
