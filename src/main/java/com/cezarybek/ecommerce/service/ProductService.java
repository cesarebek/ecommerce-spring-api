package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.dto.ProductDto;
import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.model.Seller;
import com.cezarybek.ecommerce.repository.CategoryRepository;
import com.cezarybek.ecommerce.repository.ProductRepository;
import com.cezarybek.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public Product saveProduct(ProductDto productDto) {

        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setName("tech");
        categoryRepository.save(category);
        categoryList.add(category);

        Seller seller = new Seller();
        seller.setSellerName("cesare");
        sellerRepository.save(seller);

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setInStock(productDto.getInStock());
        product.setCategories(categoryList);
        product.setSeller(seller);
        product.setReviews(new ArrayList<>());
        productRepository.save(product);
        
        return product;
    }
}
