package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.dto.AuthResponseDto;
import com.cezarybek.ecommerce.dto.LoginDto;
import com.cezarybek.ecommerce.dto.RegisterUserDto;
import com.cezarybek.ecommerce.dto.UserResponseDto;
import com.cezarybek.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public UserResponseDto registerUser(@RequestBody RegisterUserDto user) {
        return userService.registerNewUser(user);
    }

    @PostMapping("/seller/register")
    public UserResponseDto registerSeller(@RequestBody RegisterUserDto user) {
        return userService.registerNewSeller(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginAttempt) {
        return userService.login(loginAttempt);
    }


}
