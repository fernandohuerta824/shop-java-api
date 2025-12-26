package com.fernando.springboot.shop.api.shop.modules.auth;

import org.mapstruct.control.MappingControl.Use;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.springboot.shop.api.shop.domain.code.GenerateCode;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceAlreadyExistsException;
import com.fernando.springboot.shop.api.shop.modules.auth.dto.SignupDto;
import com.fernando.springboot.shop.api.shop.modules.auth.mapper.AuthMapper;
import com.fernando.springboot.shop.api.shop.modules.user.User;
import com.fernando.springboot.shop.api.shop.modules.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void signup(SignupDto dto) {
        if(dto.getEmail() != null && userRepository.existsByEmail(dto.getEmail())) {
            throw new ResourceAlreadyExistsException("El usuario con el correo " + dto.getEmail() + " ya existe");
        }

        if(dto.getPhoneNumber() != null && userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException("El usuario con el telefono " + dto.getPhoneNumber() + " ya existe");
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = authMapper.signup(dto);

        user.setCode(GenerateCode.generate());
        userRepository.save(user);

    }
}
