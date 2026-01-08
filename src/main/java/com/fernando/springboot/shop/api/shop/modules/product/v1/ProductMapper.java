package com.fernando.springboot.shop.api.shop.modules.product.v1;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.springboot.shop.api.shop.domain.mapper.BaseMapper;
import com.fernando.springboot.shop.api.shop.modules.category.Category;
import com.fernando.springboot.shop.api.shop.modules.category.v1.CategoryMapper;
import com.fernando.springboot.shop.api.shop.modules.product.Product;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductBodyDto;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductDto;
import com.fernando.springboot.shop.api.shop.modules.product.v2.dto.ProductBodyDtoV2;
import com.fernando.springboot.shop.api.shop.modules.user.UserMapper;

@Mapper(
    componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {CategoryMapper.class, UserMapper.class}
)
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
    
    @Mapping(target = "categories", ignore = true)
    ProductDto toDto(Product product, @Context boolean includeCategories, @Context CategoryMapper categoryMapper);

    @AfterMapping
    default void mapCategories(
        @MappingTarget ProductDto dto,
        Product product, 
        @Context boolean includeCategories,
        @Context CategoryMapper categoryMapper
    ) {
        if(includeCategories) {
            Set<Category> categories = product.getCategories();
            dto.setCategories(categories.isEmpty() ? HashSet.newHashSet(0) : categoryMapper.toShortDtoSet(categories));
            return;
        }

        dto.setCategories(null);
    }
    
    Product toEntity(ProductBodyDto dto);
    void updateEntity(ProductBodyDto dto, @MappingTarget Product entity);


    Product toEntity(ProductBodyDtoV2 dto);
    void updateEntity(ProductBodyDtoV2 dto, @MappingTarget Product entity);

}
