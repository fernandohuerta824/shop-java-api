package com.fernando.springboot.shop.api.shop.modules.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fernando.springboot.shop.api.shop.common.config.TrimmingStringDeserializer;
import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;
import com.fernando.springboot.shop.api.shop.modules.auth.validations.ValidContact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ValidContact
public class SignupDto {
    
    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    @Size(max = FieldLengths.MAX_EMAIL, message = "El email debe ser de maximo {max} caracteres")
    @Email(message = "El email no es valido")
    private String email;

    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    @Size(min = FieldLengths.MIN_NAME, message = "El username deber ser de minimo {min} caracteres")
    @Size(max = FieldLengths.MAX_NAME, message = "El username deber ser de maximo {max} caracteres")
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @Size(min = FieldLengths.MIN_NAME, message = "El nombre deber ser de minimo {min} caracteres")
    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    @Size(max = FieldLengths.MAX_NAME, message = "El nombre deber ser de maximo {max} caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    private String firstname;

    @Size(min = FieldLengths.MIN_NAME, message = "El apellido deber ser de minimo {min} caracteres")
    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    @Size(max = FieldLengths.MAX_NAME, message = "El apellido deber ser de maximo {max} caracteres")
    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;

    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    @Size(max = FieldLengths.MAX_PHONE_NUMBER, message = "El numero de telefono debe ser de maximo {max} caracteres")
    @Pattern(regexp = "^[1-9][0-9]{7,15}$", message = "Numero de telefono invalido")
    private String phoneNumber;

    @Size(min = 8, max = 60)
    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,60}$")
    @NotBlank(message = "La contraseña no puede ir vacia")
    private String password;
    
    @JsonDeserialize(using = TrimmingStringDeserializer.class)
    private String confirmPassword;

   
}
