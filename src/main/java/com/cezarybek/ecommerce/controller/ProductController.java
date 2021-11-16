package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.dto.ProductDto;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product addProduct(@RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }


}
