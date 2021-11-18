package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.model.Order;
import com.cezarybek.ecommerce.service.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public Order createOrder() throws NotFoundException {
        return orderService.createOrder();
    }
}
