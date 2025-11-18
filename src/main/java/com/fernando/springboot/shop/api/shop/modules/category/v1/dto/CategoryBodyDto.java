package com.fernando.springboot.shop.api.shop.modules.category.v1.dto;

import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;
import com.fernando.springboot.shop.api.shop.domain.validation.OnCreate;
import com.fernando.springboot.shop.api.shop.domain.validation.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryBodyDto {
    @NotBlank(message = "El nombre es obligatorio", groups = {OnCreate.class})
    @Size(max = FieldLengths.MAX_SHORT_NAME, groups = {OnCreate.class, OnUpdate.class}, message = "El nombre debe ser de maximo {max} caracteres" )
    private String name;

    @Size(max = FieldLengths.MAX_DESCRIPTION,  message = "La description debe ser de maximo {max} caracteres")
    private String description;

    private Long parentCategoryId;
}
