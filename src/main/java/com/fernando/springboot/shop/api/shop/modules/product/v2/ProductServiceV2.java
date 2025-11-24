package com.fernando.springboot.shop.api.shop.modules.product.v2;

import java.util.HashSet;
import java.util.Set;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.modules.category.Category;
import com.fernando.springboot.shop.api.shop.modules.category.v1.CategoryMapper;
import com.fernando.springboot.shop.api.shop.modules.product.Product;
import com.fernando.springboot.shop.api.shop.modules.product.ProductCategoryService;
import com.fernando.springboot.shop.api.shop.modules.product.ProductRepository;
import com.fernando.springboot.shop.api.shop.modules.product.v1.ProductMapper;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductDto;
import com.fernando.springboot.shop.api.shop.modules.product.v2.dto.ProductBodyDtoV2;
import com.github.f4b6a3.ulid.UlidCreator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceV2 {
    
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @Transactional
    public ProductDto save(
        ProductBodyDtoV2 body
    ) {
        Product product = productMapper.toEntity(body);
        product.setCode(UlidCreator.getUlid().toString());

        productCategoryService.addCategoriesToProduct(body.categoriesId, product);

        productRepository.save(product);
        return productMapper.toDto(product, true, categoryMapper);
    }

    @Transactional
    public ProductDto update(
        String code,
        ProductBodyDtoV2 body
    ) {
        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("El producto con el codigo " + code + "no existe"));
        
        productMapper.updateEntity(body, product);
        
        productCategoryService.updateCategories(body.getCategoriesId(), product);

        return productMapper.toDto(product);
    }
}
