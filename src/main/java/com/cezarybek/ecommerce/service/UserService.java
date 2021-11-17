package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.dto.AuthResponseDto;
import com.cezarybek.ecommerce.dto.LoginDto;
import com.cezarybek.ecommerce.dto.RegisterUserDto;
import com.cezarybek.ecommerce.dto.UserResponseDto;
import com.cezarybek.ecommerce.exception.EcommerceException;
import com.cezarybek.ecommerce.model.Cart;
import com.cezarybek.ecommerce.model.Role;
import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.repository.CartRepository;
import com.cezarybek.ecommerce.repository.RoleRepository;
import com.cezarybek.ecommerce.repository.UserRepository;
import com.cezarybek.ecommerce.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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
    private CartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponseDto registerNewUser(RegisterUserDto user) {
        checkIfUserAlreadyExist(user.getUsername(), user.getEmail());

        //Creating user
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        //Creating user -> cart (empty)
        Cart cart = new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);

        //Adding user -> roles
        addRoleToUser(user.getUsername(), "ROLE_USER");

        //Response
        return new UserResponseDto(newUser.getId(), newUser.getUsername(), newUser.getEmail());
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

    public ResponseEntity<AuthResponseDto> login(LoginDto loginAttempt) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginAttempt.getUsername(), loginAttempt.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Get token from tokenProvider class
        String token = jwtTokenProvider.generateToken(authentication);
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);
        AuthResponseDto authResponse = new AuthResponseDto(token, currentDate, expirationDate, "Bearer");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
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
