package com.fernando.springboot.shop.api.shop.modules.product.v1.dto;

import java.math.BigDecimal;

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
}
