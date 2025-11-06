package com.fernando.springboot.shop.api.shop.domain.code;

import com.github.f4b6a3.ulid.UlidCreator;

public final class GenerateCode {
    
    private GenerateCode() {};

    public static String generate() {
        return UlidCreator.getUlid().toString();
    }
}
