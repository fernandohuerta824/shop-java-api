package com.fernando.springboot.shop.api.shop.modules.product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.springboot.shop.api.shop.domain.mapper.BaseMapper;
import com.fernando.springboot.shop.api.shop.modules.product.dto.ProductBodyDto;
import com.fernando.springboot.shop.api.shop.modules.product.dto.ProductDto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
    
    Product fromBodyToEntity(ProductBodyDto dto);
    void updateProductFromDto(ProductBodyDto dto, @MappingTarget Product entity);
}
