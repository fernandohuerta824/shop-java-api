package com.fernando.springboot.shop.api.shop.modules.product.v1;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.springboot.shop.api.shop.domain.mapper.BaseMapper;
import com.fernando.springboot.shop.api.shop.modules.product.Product;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductBodyDto;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductDto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
    
    Product toEntity(ProductBodyDto dto);
    void updateEntity(ProductBodyDto dto, @MappingTarget Product entity);
}
