package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.model.Seller;
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
    public Seller saveSeller(@RequestBody Seller seller) {
        return sellerService.registerSeller(seller);
    }

    @GetMapping("/all")
    public List<Seller> allSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{sellerId}")
    public Seller getSeller(@PathVariable long sellerId) throws NotFoundException {
        return sellerService.getSellerById(sellerId);
    }

}
