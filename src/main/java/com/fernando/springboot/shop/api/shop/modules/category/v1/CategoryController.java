package com.fernando.springboot.shop.api.shop.modules.category.v1;

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
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryBodyDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryTreeDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "Se encarga de las operaciones de las categorias")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Recupera todas la categorias con paginacion")
    public ResponseEntity<ApiPageResponse<CategoryDto>> findAll(
        @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        Page<CategoryDto> pageCategories = categoryService.findAll(page);

        return BuildResponse.build(
            "Categorias recuperadas correctamente", 
            HttpStatus.OK, 
            pageCategories
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera una categoria por id")
    public ResponseEntity<ApiResponse<CategoryDto>> findById(
        @PathVariable Long id
    ) {
        CategoryDto category = categoryService.findById(id);

        return BuildResponse.build(
            "Categoria recuperada correctamente", 
            HttpStatus.OK, 
            category
        );
    }

    @PostMapping
    @Operation(summary = "Guarda una nueva categoria")
    public ResponseEntity<ApiResponse<CategoryDto>> save(
        @RequestBody @Validated(OnCreate.class) CategoryBodyDto body
    ) {
        CategoryDto category = categoryService.save(body);

        return BuildResponse.build(
            "Categoria creada correctamente", 
            HttpStatus.CREATED, 
            category
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza una categoria")
    public ResponseEntity<ApiResponse<CategoryDto>> update(
        @PathVariable Long id,
        @RequestBody @Validated(OnUpdate.class) CategoryBodyDto body
        
    ) {
        CategoryDto category = categoryService.update(id, body);

        return BuildResponse.build(
            "Categoria actualizada correctamente", 
            HttpStatus.OK, 
            category
        );
    }

    @DeleteMapping
    @Operation(summary = "Borrar una categoria y sus hijos")
    public ResponseEntity<ApiResponse<CategoryDto>> delete(
        @PathVariable Long id
    ) {
        CategoryDto category = categoryService.delete(id);

        return BuildResponse.build(
            "Category eliminada correctamente", 
            HttpStatus.OK, 
            category
        );
    }

    @GetMapping("/tree")
    public ResponseEntity<ApiPageResponse<CategoryTreeDto>> tree() {

        Page<CategoryTreeDto> categories = categoryService.tree();

        return BuildResponse.build(
            "Categorias recuperadas correctamente", 
            HttpStatus.OK, 
            categories
        );
    }
}
