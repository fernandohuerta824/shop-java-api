package com.fernando.springboot.shop.api.shop.modules.category.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fernando.springboot.shop.api.shop.domain.exception.BussinesException;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceAlreadyExistsException;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.modules.category.Category;
import com.fernando.springboot.shop.api.shop.modules.category.CategoryRepository;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryBodyDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryShortDto;
import com.fernando.springboot.shop.api.shop.modules.category.v1.dto.CategoryTreeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Page<CategoryDto> findAll(Integer page) {
        if (page < 0) {
            page = 0;
        }
        ;
        Pageable pageable = PageRequest.of(page, 20);
        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories.map(categoryMapper::toDto);
    }

    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el id " + id + " no existe"));

        return categoryMapper.toDto(category);
    }

    public CategoryDto save(CategoryBodyDto body) {
        if (categoryRepository.existsByName(body.getName())) {
            throw new ResourceAlreadyExistsException("La categoria " + body.getName() + " ya existe");
        }

        Category parentCategory = null;

        if (body.getParentCategoryId() != null) {
            parentCategory = categoryRepository.findById(body.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "La categoria padre con el id " + body.getParentCategoryId() + " no existe"));
        }

        Category category = categoryMapper.toEntity(body, parentCategory);

        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public CategoryDto update(Long id, CategoryBodyDto body) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el id " + id + "no existe"));

        if (categoryRepository.existsByNameAndIdNot(body.getName(), id)) {
            throw new ResourceAlreadyExistsException("La categoria " + body.getName() + " ya existe");
        }

        Category parentCategory = category.getParentCategory();

        if (body.getParentCategoryId() != null) {
            parentCategory = categoryRepository.findById(body.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "La categoria padre con el id " + body.getParentCategoryId() + " no existe"));

            Category current = parentCategory;
            while (current != null) {
                if (current.getId().equals(id)) {
                    throw new BussinesException("Relacion de padres ciclica detectada");
                }

                current = current.getParentCategory();
            }
        }

        categoryMapper.updateEntity(category, body, parentCategory);

        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public CategoryDto delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoria con el id " + id + " no existe"));

        categoryRepository.delete(category);

        return categoryMapper.toDto(category);
    }

    public Page<CategoryTreeDto> tree() {

        List<Category> all = categoryRepository.findAll();

        Map<Long, CategoryTreeDto> map = new HashMap<>();
        List<CategoryTreeDto> roots = new ArrayList<>();


        for (Category c : all) {
            map.put(c.getId(), categoryMapper.toTreeDto(c));
        }


        for (Category c : all) {
            CategoryTreeDto dto = map.get(c.getId());

            if (c.getParentCategory() == null) {
                roots.add(dto);
            } else {
                map.get(c.getParentCategory().getId())
                        .getChildCategories()
                        .add(dto);
            }
        }

        return new PageImpl<>(
                roots,
                PageRequest.of(0, roots.size()),
                roots.size());
    }

    public Page<CategoryShortDto> findCategoryChildren(Long id, Integer page) {

        Category category = null;

        if(id > 0) {
            category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("La categoria con el id " + id + " no existe"));
        }

        if(page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 20);

        Page<Category> categories = categoryRepository.findByParentCategory(category, pageable);

        return categories.map(categoryMapper::toShortDto);
    }
}
