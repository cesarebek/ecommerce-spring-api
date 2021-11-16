package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private UserRepository userRepository;

    public User registerSeller(User seller) {
        return userRepository.save(seller);
    }

    public List<User> getAllSellers() {
        return userRepository.findAll();
    }

    public User getSellerById(long sellerId) throws NotFoundException {
        Optional<User> seller = userRepository.findById(sellerId);
        if (seller.isEmpty()) throw new NotFoundException("Seller not found");
        return seller.get();
    }
}
