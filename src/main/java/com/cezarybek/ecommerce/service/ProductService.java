package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.repository.CategoryRepository;
import com.cezarybek.ecommerce.repository.ProductRepository;
import com.cezarybek.ecommerce.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public Product saveProduct(Product product) {

        long sellerId = 2;
        User seller = userRepository.findById(sellerId).get();
        product.setSeller(seller);

        productRepository.save(product);
        //Attach categories to product
        addCategoryToProduct("tech", product.getId());
        addCategoryToProduct("phone", product.getId());
        addCategoryToProduct("apple", product.getId());

        return product;
    }

    public void addCategoryToProduct(String categoryName, long productId) {
        Category category = categoryRepository.getCategoryByName(categoryName);
        Product product = productRepository.getById(productId);
        product.getCategories().add(category);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long productId) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new NotFoundException("Category not found");
        return product.get();
    }
}
