package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> allCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/save")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable long categoryId) throws NotFoundException {
        return categoryService.getCategoryById(categoryId);
    }
}
