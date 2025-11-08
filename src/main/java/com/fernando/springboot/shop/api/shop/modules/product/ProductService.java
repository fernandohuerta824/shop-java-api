package com.fernando.springboot.shop.api.shop.modules.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;
import com.fernando.springboot.shop.api.shop.domain.code.GenerateCode;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.modules.product.dto.ProductBodyDto;
import com.fernando.springboot.shop.api.shop.modules.product.dto.ProductDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto save(ProductBodyDto body) {
        Product product = productMapper.toEntity(body);
        product.setCode(GenerateCode.generate());

        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Boolean isAvailable,
        Integer page
    ) {
        if(page < 0) {
            page = 0;
        }

        Page<Product> productPage = productRepository.findAll(
            ProductSpecification.hasName(name)
            .and(ProductSpecification.priceBetween(minPrice, maxPrice))
            .and(ProductSpecification.isAvailable(isAvailable)),
            PageRequest.of(page, 20)
        );

        return productPage.map(productMapper::toDto);
    }

    @Transactional(readOnly = true)
    public ProductDto findByCode(String code) {
        var ex = new ResourceNotFoundException("El producto no pudo ser encontrado");
        if(code.length() < FieldLengths.MAX_CODE) {
            throw ex;
        }

        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> ex);

        return productMapper.toDto(product);
    }

    @Transactional
    public ProductDto update(String code, ProductBodyDto body) {
        var ex = new ResourceNotFoundException("El producto no pudo ser encontrado");
        if(code.length() < FieldLengths.MAX_CODE) {
            throw ex;
        }

        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> ex);

        productMapper.updateEntity(body, product);

        return productMapper.toDto(product);
    }
    
    @Transactional
    public ProductDto delete(String code) { 
        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("El producto no pudo ser encontrado"));

        productRepository.delete(product);

        return productMapper.toDto(product);
    }
}
