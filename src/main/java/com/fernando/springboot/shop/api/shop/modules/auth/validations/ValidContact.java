package com.fernando.springboot.shop.api.shop.modules.auth.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidContactValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContact {
    String message() default "Debe proporcionar email o número de teléfono";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
