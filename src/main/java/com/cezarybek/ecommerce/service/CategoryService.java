package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.repository.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long categoryId) throws NotFoundException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) throw new NotFoundException("Category not found");
        return category.get();
    }
}
