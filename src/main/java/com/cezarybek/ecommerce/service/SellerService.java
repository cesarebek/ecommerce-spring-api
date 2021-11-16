package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.model.Seller;
import com.cezarybek.ecommerce.repository.SellerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;


    public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(long sellerId) throws NotFoundException {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller.isEmpty()) throw new NotFoundException("Seller not found");
        return seller.get();
    }
}
