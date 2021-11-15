package com.cezarybek.ecommerce.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    private Long id;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;
}
