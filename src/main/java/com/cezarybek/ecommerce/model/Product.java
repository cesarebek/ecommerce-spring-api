package com.cezarybek.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Product {
    @Id
    private Long id;
    private String productName;
    private Integer inStock;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;
    @ManyToOne
    private Seller seller;
}
