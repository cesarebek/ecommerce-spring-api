package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.dto.ProductDto;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public Product addProduct(@RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/all")
    public List<Product> allProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable long productId) throws NotFoundException {
        return productService.getProductById(productId);
    }

    @PostMapping("/{productId}/category/{categoryId}")
    public Product addCategoryToProduct(@PathVariable final long productId, @PathVariable final long categoryId) {
        return productService.addCategoryToProduct(categoryId, productId);
    }

    @GetMapping("/all/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable final long categoryId) throws NotFoundException {
        return productService.getProductsByCategory(categoryId);
    }

    @DeleteMapping("/{productId}/category/{categoryId}")
    public String removeCategoryFromProduct(@PathVariable final long productId,
                                            @PathVariable final long categoryId) throws NotFoundException {
        return productService.removeCategoryFromProduct(categoryId, productId);
    }
}
