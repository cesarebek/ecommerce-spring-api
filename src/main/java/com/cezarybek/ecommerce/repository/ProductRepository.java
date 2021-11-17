package com.cezarybek.ecommerce.repository;

import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductByCategoriesContaining(Category categories);
}
