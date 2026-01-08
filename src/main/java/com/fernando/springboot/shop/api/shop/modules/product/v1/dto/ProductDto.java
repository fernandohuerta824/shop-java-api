package com.fernando.springboot.shop.api.shop.modules.product.v1.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryShortDto;
import com.fernando.springboot.shop.api.shop.modules.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    
    private String name;
    private String description;
    private String code;
    private BigDecimal price;
    private Integer stock;
    private Boolean isAvailable;
    private Integer discount;
    private String imageUrl;
    private UserDto user;

    @JsonInclude(value  = Include.NON_NULL)
    private Set<CategoryShortDto> categories;
}
