package com.fernando.springboot.shop.api.shop.modules.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fernando.springboot.shop.api.shop.common.config.TrimmingStringDeserializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDto {

    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    private String identifier;

    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    public String password;
}
