package com.fernando.springboot.shop.api.shop.modules.user;

import org.mapstruct.Mapper;

import com.fernando.springboot.shop.api.shop.domain.mapper.BaseMapper;
import com.fernando.springboot.shop.api.shop.modules.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
    
}
