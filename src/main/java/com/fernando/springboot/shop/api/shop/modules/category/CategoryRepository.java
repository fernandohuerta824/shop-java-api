package com.fernando.springboot.shop.api.shop.modules.category;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    List<Category> findByParentCategory(Category parentCategory);

    Page<Category> findByParentCategory(Category parentCategory, Pageable page);
}
