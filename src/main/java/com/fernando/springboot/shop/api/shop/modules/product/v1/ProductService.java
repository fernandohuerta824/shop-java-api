package com.fernando.springboot.shop.api.shop.modules.product.v1;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Locale.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;
import com.fernando.springboot.shop.api.shop.domain.code.GenerateCode;
import com.fernando.springboot.shop.api.shop.domain.exception.ResourceNotFoundException;
import com.fernando.springboot.shop.api.shop.modules.category.v1.CategoryMapper;
import com.fernando.springboot.shop.api.shop.modules.cloudinary.CloudinaryService;
import com.fernando.springboot.shop.api.shop.modules.product.Product;
import com.fernando.springboot.shop.api.shop.modules.product.ProductRepository;
import com.fernando.springboot.shop.api.shop.modules.product.ProductSpecification;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductBodyDto;
import com.fernando.springboot.shop.api.shop.modules.product.v1.dto.ProductDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CloudinaryService cloudinaryService;
    private final CategoryMapper categoryMapper;

    @Transactional
    public ProductDto save(ProductBodyDto body) {
        Product product = productMapper.toEntity(body);
        product.setCode(GenerateCode.generate());

        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Boolean isAvailable,
        Integer page,
        String sortDir,
        String sortBy,
        Boolean includeCategories
    ) {
        if(page < 0) {
            page = 0;
        }

        Sort sort = Sort.unsorted();

        List<String> allowedSortFields = List.of("name", "price");

        Specification<Product> spec = ProductSpecification.hasName(name)
            .and(ProductSpecification.priceBetween(minPrice, maxPrice))
            .and(ProductSpecification.isAvailable(isAvailable));

        if(sortBy != null && allowedSortFields.contains(sortBy)) {
            Direction dir = sortDir.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;

            if(sortBy.equalsIgnoreCase("price")) {
                spec = spec.and(
                    ProductSpecification.orderByFinalPrice(dir)
                );
            } else {
                sort = Sort.by(dir, sortBy);
            }
        }

        Page<Product> productPage = productRepository.findAll(
            spec,
            PageRequest.of(page, 20, sort)
        );

        return productPage.map(r -> productMapper.toDto(r, includeCategories, categoryMapper));
    }

    @Transactional(readOnly = true)
    public ProductDto findByCode(String code) {
        var ex = new ResourceNotFoundException("El producto no pudo ser encontrado");
        if(code.length() < FieldLengths.MAX_CODE) {
            throw ex;
        }

        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> ex);

        return productMapper.toDto(product);
    }

    @Transactional
    public ProductDto update(String code, ProductBodyDto body) {
        var ex = new ResourceNotFoundException("El producto no pudo ser encontrado");
        if(code.length() < FieldLengths.MAX_CODE) {
            throw ex;
        }

        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> ex);

        productMapper.updateEntity(body, product);

        return productMapper.toDto(product);
    }
    
    @Transactional
    public ProductDto delete(String code) { 
        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("El producto no pudo ser encontrado"));

        productRepository.delete(product);

        return productMapper.toDto(product);
    }

    @Transactional
    public Map<?, ?> uploadImage(
        String code,
        MultipartFile image
    ) throws Exception  {
        Product product = productRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("El producto no pudo ser encontrado"));

        Map<?, ?> cloudinaryResult = null;
        try {
            cloudinaryResult = cloudinaryService.uploadImage(image, "products");
        } catch (Exception e) {
            throw new Exception("Error al subir la imagen");
        }


        if(product.getPublicImageId() != null) {
            try {
                cloudinaryService.deleteImage(product.getPublicImageId());
            } catch (Exception e) {
            
            }
            
        }
        product.setImageUrl(cloudinaryResult.get("url").toString());
        product.setPublicImageId(cloudinaryResult.get("publicId").toString());
        
        productRepository.save(product);
        
        return cloudinaryResult;
    }

    @Transactional
    public void deleteImage(
        String code
    ) throws Exception {
       Product product = productRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("El producto no pudo ser encontrado"));

        try {
            cloudinaryService.deleteImage(product.getPublicImageId());
        } catch (Exception e) {
             throw new Exception("Error al subir la imagen");
        }

        product.setImageUrl(null);
        product.setPublicImageId(null);

        productRepository.save(product);

        
    }
}
