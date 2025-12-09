package com.fernando.springboot.shop.api.shop.modules.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Expression;

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

            Expression<BigDecimal> discount = cb.coalesce(root.get("discount"), BigDecimal.ZERO);
            Expression<BigDecimal> finalPrice = cb.diff(
                root.get("price"), 
                cb.prod(root.get("price"), cb.quot(discount, BigDecimal.valueOf(100))).as(BigDecimal.class)
            );

            if(minPrice != null && maxPrice != null) {
                return cb.between(
                    finalPrice, 
                    minPrice, 
                    maxPrice
                );
            }
            
            if(minPrice != null) {
                return cb.greaterThanOrEqualTo(
                    finalPrice,
                    minPrice
                );
            }

            return cb.lessThanOrEqualTo(
                finalPrice,
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
    
    public static Specification<Product> orderByFinalPrice(Direction dir) {
    return (root, query, cb) -> {
        Expression<BigDecimal> discount = cb.coalesce(root.get("discount"), BigDecimal.ZERO);
            Expression<BigDecimal> finalPrice = cb.diff(
                root.get("price"), 
                cb.prod(root.get("price"), cb.quot(discount, BigDecimal.valueOf(100))).as(BigDecimal.class)
            );

        query.orderBy(
            dir == Direction.DESC
                ? cb.desc(finalPrice)
                : cb.asc(finalPrice)
        );

        return cb.conjunction();
    };
}
    
}
