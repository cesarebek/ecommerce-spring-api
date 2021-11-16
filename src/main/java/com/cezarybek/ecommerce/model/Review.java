package com.cezarybek.ecommerce.model;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rate;
    private String text;
    @ManyToOne
    private Product product;
}
