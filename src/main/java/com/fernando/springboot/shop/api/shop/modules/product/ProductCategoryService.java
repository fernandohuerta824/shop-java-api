package com.fernando.springboot.shop.api.shop.modules.product;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.fernando.springboot.shop.api.shop.domain.exception.BussinesException;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.modules.category.Category;
import com.fernando.springboot.shop.api.shop.modules.category.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductCategoryService {
    
    private CategoryRepository categoryRepository;

    public void addCategoriesToProduct(Set<Long> categoriesId) {

        if(categoriesId.isEmpty()) {
            throw new BussinesException("El producto debe tener al menos una categoria");
        }

        Long rootId = categoriesId.stream().min(Long::compareTo).orElseThrow(() -> new BussinesException("El producto debe tener al menos una categoria"));
        Category root = categoryRepository.findById(rootId).orElseThrow(() -> new ResourceNotFoundException("La cateogria con el id" + rootId +  " no existe"));

        if(categoriesId.size() > 10) {
            throw new BussinesException("Un producto no puede tener mas 10 categorias");
        }
    }
}
