package com.fernando.springboot.shop.api.shop.modules.product;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {
    
    private ProductSpecification() {}

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> {
            if(name == null || name.isBlank() || name.length() < 4) {
                return null;
            }

            return cb.like(
                cb.lower(root.get("name")), 
                "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, cb) -> {
            if(minPrice == null && maxPrice == null) {
                return null;
            }

            if(minPrice != null && maxPrice != null) {
                return cb.between(
                    root.get("price"), 
                    minPrice, 
                    maxPrice
                );
            }

            if(minPrice != null) {
                return cb.greaterThanOrEqualTo(
                    root.get("price"), 
                    minPrice
                );
            }

            return cb.lessThanOrEqualTo(
                root.get("price"), 
                maxPrice
            );
        };
    }

    public static Specification<Product> isAvailable(Boolean isAvailable)  {
        return (root, query, cb) -> {
            if(isAvailable == null) {
                return null;
            }

            if(isAvailable) {
                return cb.and(
                    cb.equal(root.get("isAvailable"), true),
                    cb.greaterThan(root.get("stock"), 0)
                );
            }
            
            return cb.or(
                cb.equal(root.get("isAvailable"), false),
                cb.lessThan(root.get("stock"), 1)
            );
        };

    }
    
    
}
