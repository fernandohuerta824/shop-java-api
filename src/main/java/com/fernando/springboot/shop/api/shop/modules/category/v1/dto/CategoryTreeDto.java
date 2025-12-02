package com.fernando.springboot.shop.api.shop.modules.category.v1.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryTreeDto {
    private Long id;
    private String name;
    private String description;
    private Set<CategoryTreeDto> childCategories = new HashSet<>();
}
