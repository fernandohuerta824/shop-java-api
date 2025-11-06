package com.fernando.springboot.shop.api.shop.modules.product.dto;

import java.math.BigDecimal;

import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;
import com.fernando.springboot.shop.api.shop.domain.validation.OnCreate;
import com.fernando.springboot.shop.api.shop.domain.validation.OnUpdate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductBodyDto {

    @NotNull(message = "El nombre es obligatorio", groups = {OnCreate.class})
    @Size(max = FieldLengths.MAX_NAME, message = "El nombre deber ser de maximo {max} caracteres", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Size(max = FieldLengths.MAX_DESCRIPTION, message = "La descripcion deber ser de maximo {max} caracteres", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @NotNull(message = "El precio es obligatorio", groups = {OnCreate.class})
    @Positive(message = "El precio debe ser mayor a cero", groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 99_999, message = "El precio deber ser menor a 100,000", groups = {OnCreate.class, OnUpdate.class})
    private BigDecimal price;

    @NotNull(message = "El stock es obligatorio", groups = {OnCreate.class})
    @Min(value = 0, message = "El stock debe ser mayor a 0", groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 999, message = "El stock debe ser menor a 1,000", groups = {OnCreate.class, OnUpdate.class})
    private Integer stock;

    @Min(value = 0, message = "El descuento deber mayor a 0", groups = {OnCreate.class, OnUpdate.class})
    @Max(value =  100, message = "El descuento no puede ser mayor de 100", groups = {OnCreate.class, OnUpdate.class})
    private Integer discount;

    @NotNull(message = "La disponibilidad es obligatoria", groups = {OnCreate.class})
    private Boolean isAvailable;

}
