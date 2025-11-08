package com.fernando.springboot.shop.api.shop.modules.category;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.fernando.springboot.shop.api.shop.domain.mapper.BaseMapper;
import com.fernando.springboot.shop.api.shop.modules.category.dto.CategoryDto;
import com.fernando.springboot.shop.api.shop.modules.category.dto.CategoryShortDto;

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
}
