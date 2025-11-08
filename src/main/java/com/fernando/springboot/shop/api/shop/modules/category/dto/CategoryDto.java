package com.fernando.springboot.shop.api.shop.modules.category.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryDto {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String description;

    private CategoryShortDto parentCategory;
}