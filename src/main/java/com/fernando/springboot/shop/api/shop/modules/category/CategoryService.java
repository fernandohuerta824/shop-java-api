package com.fernando.springboot.shop.api.shop.modules.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fernando.springboot.shop.api.shop.modules.category.dto.CategoryDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public Page<CategoryDto> findAll(Integer page) {
        if(page < 0) {
            page = 0;
        };
        Pageable pageable = PageRequest.of(page, 20);
        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories.map(categoryMapper::toDto);
    }
}
