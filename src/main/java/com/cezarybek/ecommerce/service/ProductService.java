package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.dto.ProductDto;
import com.cezarybek.ecommerce.exception.EcommerceException;
import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Product;
import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.repository.CategoryRepository;
import com.cezarybek.ecommerce.repository.ProductRepository;
import com.cezarybek.ecommerce.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public Product saveProduct(ProductDto product) {

        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setInStock(product.getInStock());

        long sellerId = 3;
        User seller = userRepository.findById(sellerId).get();
        newProduct.setSeller(seller);

        productRepository.save(newProduct);

        //Attach categories to product
        for (Long category : product.getCategoryIds()) {
            addCategoryToProduct(category, newProduct.getId());
        }
        //Response
        return newProduct;
    }

    public Product addCategoryToProduct(long categoryId, long productId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Product> product = productRepository.findById(productId);
        if (category.isEmpty() || product.isEmpty())
            throw new EcommerceException("Category or product not found", HttpStatus.BAD_REQUEST);
        product.get().getCategories().add(category.get());
        return product.get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long productId) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new NotFoundException("Product not found");
        return product.get();
    }

    public List<Product> getProductsByCategory(long categoryId) throws NotFoundException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) throw new NotFoundException(String.format("Category with ID %s not found", categoryId));

        return productRepository.getProductByCategoriesContaining(category.get());
    }

    public String removeCategoryFromProduct(long categoryId, long productId) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new NotFoundException("Product not found");
        product.get().getCategories().removeIf(c -> c.getId() == categoryId);
        return String.format("Category with ID %s removed from %s", categoryId, product.get().getName());
    }

    public Product updateProductById(long productId, ProductDto productDto) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new NotFoundException("Product not found");

        if (productDto.getName() != null) product.get().setName(productDto.getName());
        if (productDto.getPrice() != null) product.get().setPrice(productDto.getPrice());
        if (productDto.getInStock() != null) product.get().setInStock(productDto.getInStock());
        if (productDto.getCategoryIds() != null) {
            product.get().getCategories().clear();
            for (Long category : productDto.getCategoryIds()) {
                addCategoryToProduct(category, product.get().getId());
            }
        }

        return product.get();
    }
}
