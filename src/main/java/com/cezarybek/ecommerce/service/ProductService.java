package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.dto.ProductDto;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(ProductDto productDto) {
        Product product = new Product(
                productDto.getProductName(),
                productDto.getInStock(),
                productDto.getCategoryList(),
                productDto.getSeller());
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return product;
    }
}
