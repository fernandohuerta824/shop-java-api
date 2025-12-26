package com.fernando.springboot.shop.api.shop.modules.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.fernando.springboot.shop.api.shop.modules.auth.dto.SignupDto;
import com.fernando.springboot.shop.api.shop.modules.user.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthMapper  {
    
    User signup(SignupDto dto);
}
