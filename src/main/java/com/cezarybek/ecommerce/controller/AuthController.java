package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.dto.RegisterUserDto;
import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public User registerUser(@RequestBody RegisterUserDto user) {
        return userService.registerNewUser(user);
    }

    @PostMapping("/seller/register")
    public User registerSeller() {
        return userService.registerNewSeller();
    }

    @PostMapping("/login")
    public User login() {
        return userService.login();
    }


}
