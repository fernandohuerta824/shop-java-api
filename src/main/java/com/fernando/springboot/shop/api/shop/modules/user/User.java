package com.fernando.springboot.shop.api.shop.modules.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Check(constraints = "email IS NOT NULL OR phone_number IS NOT NULL")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = FieldLengths.MAX_CODE, updatable = false)
    @EqualsAndHashCode.Include
    private String code;

    @Column(unique = true, nullable = false, length = FieldLengths.MAX_NAME)
    private String username;

    @Column(nullable = false, length = FieldLengths.MAX_NAME)
    private String firstname;

    @Column(length = FieldLengths.MAX_NAME)
    private String lastname;

    @Column(unique = true, length = FieldLengths.MAX_EMAIL)
    private String email;

    @Column(unique = true, name = "phone_number", length = FieldLengths.MAX_PHONE_NUMBER)
    private String phoneNumber;

    @Column(nullable = false, length = FieldLengths.MAX_PASSWORD)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
