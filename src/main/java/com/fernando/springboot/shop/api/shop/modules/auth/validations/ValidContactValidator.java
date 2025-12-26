package com.fernando.springboot.shop.api.shop.modules.auth.validations;

import com.fernando.springboot.shop.api.shop.modules.auth.dto.SignupDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidContactValidator implements ConstraintValidator<ValidContact, SignupDto> {

    @Override
    public boolean isValid(SignupDto dto, ConstraintValidatorContext ctx) {

        if (dto == null) {
            return true;
        }
        boolean hasEmail = dto.getEmail() != null && !dto.getEmail().isBlank();
        boolean hasPhone = dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank();

        if (hasEmail || hasPhone) {
            return true;
        }

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(
                "Debe proporcionar email o número de teléfono")
                .addPropertyNode("email")
                .addConstraintViolation();

        return false;

    }

}
