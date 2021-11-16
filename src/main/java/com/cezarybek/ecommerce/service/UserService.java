package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.dto.RegisterUserDto;
import com.cezarybek.ecommerce.exception.EcommerceException;
import com.cezarybek.ecommerce.model.Role;
import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.repository.RoleRepository;
import com.cezarybek.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerNewUser(RegisterUserDto user) {
        checkIfUserAlreadyExist(user.getUsername(), user.getEmail());
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        addRoleToUser(user.getUsername(), "ROLE_USER");

        return newUser;
    }

    public void addRoleToUser(String userName, String roleName) {
        Optional<Role> role = roleRepository.findByName(roleName);
        Optional<User> user = userRepository.findByUsername(userName);
        if (role.isEmpty() || user.isEmpty())
            throw new EcommerceException("User or Role not found", HttpStatus.BAD_REQUEST);
        user.get().getRoles().add(role.get());
    }

    public User registerNewSeller() {
        return null;
    }

    public User login() {
        return null;
    }

    public void checkIfUserAlreadyExist(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new EcommerceException("Username is already taken!", HttpStatus.IM_USED);
        }
        if (userRepository.existsByEmail(email)) {
            throw new EcommerceException("Email is already register, please login.", HttpStatus.IM_USED);
        }
    }
}
