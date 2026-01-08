package com.fernando.springboot.shop.api.shop.modules.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByCode(String code);

    boolean existsByCodeAndUser_Code(String code, String userCode);

    boolean existsByCode(String code);
}
