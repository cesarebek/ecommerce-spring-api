package com.cezarybek.ecommerce;

import com.cezarybek.ecommerce.dto.RegisterUserDto;
import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Role;
import com.cezarybek.ecommerce.service.CategoryService;
import com.cezarybek.ecommerce.service.RoleService;
import com.cezarybek.ecommerce.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);

    }

    //    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, CategoryService categoryService) {
        return args -> {
            //Adding Roles to database
            roleService.saveRole(new Role("ROLE_ADMIN"));
            roleService.saveRole(new Role("ROLE_USER"));

            //Adding Users to database with default ROLE_USER
            userService.registerNewUser(new RegisterUserDto("cesare", "cesarebek1@gmail.com", "pass123"));
            userService.registerNewUser(new RegisterUserDto("elisa", "elisa1999@gmail.com", "pass123"));

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
        };
    }
}

