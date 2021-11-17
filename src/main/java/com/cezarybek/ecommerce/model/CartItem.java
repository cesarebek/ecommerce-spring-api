package com.cezarybek.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Double subTotal;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
}
