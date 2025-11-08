package com.fernando.springboot.shop.api.shop.common.config;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fernando.springboot.shop.api.shop.domain.code.GenerateCode;
import com.fernando.springboot.shop.api.shop.modules.category.CategoryRepository;
import com.fernando.springboot.shop.api.shop.modules.product.Product;
import com.fernando.springboot.shop.api.shop.modules.product.ProductRepository;

import lombok.AllArgsConstructor;
import net.datafaker.Faker;

@Configuration
@AllArgsConstructor
public class SeedConfig implements CommandLineRunner {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    

    @Override
    public void run(String... args) throws Exception {
        
        Faker faker = new Faker(new Locale("es"));
        Random ran = new Random();

        if(productRepository.count() < 1) {
            for(int i = 0; i < 10_000; i ++) {
                Product p = new Product();
    
                p.setName(faker.commerce().productName());
                p.setCode(GenerateCode.generate());
                p.setDescription(faker.lorem().sentence(20));
                p.setPrice(
                    BigDecimal.valueOf(
                        faker.number().randomDouble(2, 30, 2000)
                    )
                );
    
                if(ran.nextInt(0, 2) == 1) {
                    p.setDiscount(faker.number().numberBetween(1, 20));
                }
    
                p.setStock(faker.number().numberBetween(0, 100));
    
                p.setIsAvailable(true);
    
                productRepository.save(p);
            }
        }

    }
}
