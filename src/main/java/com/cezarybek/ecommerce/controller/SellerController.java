package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.model.User;
import com.cezarybek.ecommerce.service.SellerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping("/save")
    public User saveSeller(@RequestBody User seller) {
        return sellerService.registerSeller(seller);
    }

    @GetMapping("/all")
    public List<User> allSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{sellerId}")
    public User getSeller(@PathVariable long sellerId) throws NotFoundException {
        return sellerService.getSellerById(sellerId);
    }

}
