package com.cezarybek.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    private Long id;
    private int quantity;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
}
