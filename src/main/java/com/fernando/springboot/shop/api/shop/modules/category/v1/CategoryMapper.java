package com.fernando.springboot.shop.api.shop.modules.category.v1;

import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.springboot.shop.api.shop.domain.mapper.BaseMapper;
import com.fernando.springboot.shop.api.shop.modules.category.Category;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryBodyDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryShortDto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper extends BaseMapper<Category, CategoryDto> {
    
    @AfterMapping
    default void mapParentDto(
        @MappingTarget CategoryDto dto,
        Category entity
    ) {
        if(entity.getParentCategory()== null) {
            dto.setParentCategory(null);
            return;
        }

        CategoryShortDto shortDto = toShortDto(entity.getParentCategory());

        dto.setParentCategory(shortDto);
    }

    @Mapping(target = "parentCategory", ignore = true)
    CategoryShortDto toShortDto(Category entity);

    Set<CategoryShortDto> toShortDtoSet(Set<Category> categories);

    @AfterMapping
    default void mapCategoryId(
        @MappingTarget CategoryShortDto dto,
        Category entity
    ) {
        if(entity.getParentCategory() == null) {
            dto.setParentCategory(null);
            return;
        }

        dto.setParentCategory(entity.getParentCategory().getId());
    }

    Category toEntity(CategoryBodyDto dto, @Context Category parentCategory );

    @AfterMapping
    default void setParentCategory(
        @MappingTarget Category entity, 
        CategoryBodyDto dto,
        @Context Category parentCategory
    ) {
        entity.setParentCategory(parentCategory);
    }

    void updateEntity(
        @MappingTarget Category entity, 
        CategoryBodyDto dto,
        @Context Category parentCategory
    );
}
