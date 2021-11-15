package com.cezarybek.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    private Long id;
    private int quantity;
    private Double subTotal;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
}
