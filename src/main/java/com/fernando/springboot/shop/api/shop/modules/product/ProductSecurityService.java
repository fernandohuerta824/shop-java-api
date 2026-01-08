package com.fernando.springboot.shop.api.shop.modules.product;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.springboot.shop.api.shop.common.constants.FieldLengths;
import com.fernando.springboot.shop.api.shop.domain.security.SecurityUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductSecurityService {
    
    private final ProductRepository productRepository;


    @Transactional(readOnly = true)
    public boolean isOwner(String productCode) {
        if(productCode.length() != FieldLengths.MAX_CODE) {
            return true;
        }
        String code = SecurityUtils.getCurrentUserCode();
        
     
        if(!productRepository.existsByCode(productCode)) return true;
            
        
        return productRepository.existsByCodeAndUser_Code(productCode, code);
    }
}
