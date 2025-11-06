package com.fernando.springboot.shop.api.shop.modules.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.springboot.shop.api.shop.domain.response.ApiPageResponse;
import com.fernando.springboot.shop.api.shop.domain.response.ApiResponse;
import com.fernando.springboot.shop.api.shop.domain.response.BuildResponse;
import com.fernando.springboot.shop.api.shop.domain.validation.OnCreate;
import com.fernando.springboot.shop.api.shop.domain.validation.OnUpdate;
import com.fernando.springboot.shop.api.shop.modules.product.dto.ProductBodyDto;
import com.fernando.springboot.shop.api.shop.modules.product.dto.ProductDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Tag(name = "Products", description =  "Se encarga de las operaciones de lo productos")
public class ProductController {
    
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Devuelve una pagina de products")
    public ResponseEntity<ApiPageResponse<ProductDto>> findAll(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice,
        @RequestParam(required = false) Boolean isAvailable,
        @RequestParam(required = false, defaultValue = "0") Integer page

    ) {
        Page<ProductDto> pageProduct = productService.findAll(name, minPrice, maxPrice, isAvailable, page);

        return BuildResponse.build("Productos recuperados correctamente", HttpStatus.OK, pageProduct);

    }

    @GetMapping("/{code}")
    @Operation(summary = "Devuelve un producto por el codigo")
    public ResponseEntity<ApiResponse<ProductDto>> findByCode(
        @PathVariable String code
    ) {
        ProductDto product = productService.findByCode(code);

        return BuildResponse.build("Producto recuperado correctamente", HttpStatus.OK, product);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo producto")
    public ResponseEntity<ApiResponse<ProductDto>> save(
        @RequestBody @Validated(OnCreate.class) ProductBodyDto body
    ) {
        ProductDto product = productService.save(body);

        return BuildResponse.build("Producto creado correctamente", HttpStatus.CREATED, product);
    }

    @PatchMapping("/{code}")
    @Operation(summary = "Actualiza un product existente")
    public ResponseEntity<ApiResponse<ProductDto>> update(
        @PathVariable String code,
        @Validated(OnUpdate.class) @RequestBody ProductBodyDto body
    ) {
        ProductDto product = productService.update(code, body);

        return BuildResponse.build("Producto actualizado correctamente", HttpStatus.OK, product);
    }

    @DeleteMapping("/{code}")
    @Operation(summary = "Elimina un producto por id")
    public ResponseEntity<ApiResponse<ProductDto>> delete(
        @PathVariable String code
    ) {
        ProductDto product = productService.delete(code);

        return BuildResponse.build("Producto eliminado correctamente", HttpStatus.OK, product);
    }
}
