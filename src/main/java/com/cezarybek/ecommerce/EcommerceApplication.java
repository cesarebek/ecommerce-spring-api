package com.cezarybek.ecommerce;

import com.cezarybek.ecommerce.dto.ProductDto;
import com.cezarybek.ecommerce.dto.RegisterUserDto;
import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Role;
import com.cezarybek.ecommerce.service.CategoryService;
import com.cezarybek.ecommerce.service.ProductService;
import com.cezarybek.ecommerce.service.RoleService;
import com.cezarybek.ecommerce.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);

    }

    //    @Bean
    CommandLineRunner run(UserService userService,
                          RoleService roleService,
                          CategoryService categoryService,
                          ProductService productService) {
        return args -> {
            //Adding Roles to database
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_USER"));
            roleService.saveRole(new Role("ROLE_SELLER"));

            //Adding Users to database with default ROLE_USER
            userService.registerNewUser(new RegisterUserDto("cesare", "cesarebek1@gmail.com", "pass123"));
            userService.registerNewUser(new RegisterUserDto("elisa", "elisa1999@gmail.com", "pass123"));
            userService.registerNewSeller(new RegisterUserDto("mario", "mario@gmail.com", "pass123"));

            //Adding ROLE_ADMIN to Users
            userService.addRoleToUser("cesare", "ROLE_ADMIN");

            //Adding Categories
            categoryService.saveCategory(new Category("Tech"));
            categoryService.saveCategory(new Category("Pet"));
            categoryService.saveCategory(new Category("Food"));
            categoryService.saveCategory(new Category("Books"));
            categoryService.saveCategory(new Category("Home"));
            categoryService.saveCategory(new Category("Sport"));
            categoryService.saveCategory(new Category("Music"));

            //Adding Products
            productService.saveProduct(new ProductDto("iPhone", 1299.99, 100, new ArrayList<>(Arrays.asList(1l))));
            productService.saveProduct(new ProductDto("Bike", 2999.99, 100, new ArrayList<>(Arrays.asList(6l))));
            productService.saveProduct(new ProductDto("Home Gym", 1999.99, 100, new ArrayList<>(Arrays.asList(5l, 6l))));
            productService.saveProduct(new ProductDto("PS5", 399.99, 100, new ArrayList<>(Arrays.asList(1l))));
            productService.saveProduct(new ProductDto("I Promessi Sposi", 59.99, 1000, new ArrayList<>(Arrays.asList(4l))));
            productService.saveProduct(new ProductDto("Pet Food RoyalCanin", 89.99, 1000, new ArrayList<>(Arrays.asList(2l))));
        };
    }
}

