package com.fernando.springboot.shop.api.shop.modules.product;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    private void validateHierarchy(List<Category> categories) {

        Set<Category> set = new HashSet<>(categories);

        Set<Category> rootCandidates = new HashSet<>();
        for (Category c : categories) {
            Category cur = c.getParentCategory();
            boolean isChildOfSet = false;

            while (cur != null) {
                if (set.contains(cur)) {
                    isChildOfSet = true;
                    break;
                }
                cur = cur.getParentCategory();
            }

            if (!isChildOfSet) {
                rootCandidates.add(c);
            }
        }
        
        Category aux = rootCandidates.stream().findFirst().get();

        for(Category root: rootCandidates) {
            System.out.println("Equal?: " + Objects.equals(root, aux));
            if(Objects.equals(root, aux)) continue;
            
            Category rootParent = root.getParentCategory();
            if((aux == null && rootParent == null) || !Objects.equals(root.getParentCategory(), aux)) {
                throw new BussinesException("Jerarquia no valida de categorias");
            }
        }

    }

    public void addCategoriesToProduct(Set<Long> categoriesId, Product product) {

        if (categoriesId.isEmpty()) {
            throw new BussinesException("El producto debe tener al menos una categoria");
        }

        if (categoriesId.size() > 4) {
            throw new BussinesException("Un producto no puede tener mas 4 categorias");
        }

        List<Category> categories = categoryRepository.findAllById(categoriesId);

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Ninguna categoria fue encontrada");
        }

        validateHierarchy(categories);

        product.setCategories(new HashSet<>(categories));
    }
}
