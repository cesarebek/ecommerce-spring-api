package com.cezarybek.ecommerce.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "[order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private LocalDateTime createdAt;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
