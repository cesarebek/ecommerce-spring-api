package com.cezarybek.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    public Product(String productName, Integer inStock, List<Category> categories, Seller seller) {
        this.productName = productName;
        this.inStock = inStock;
        this.categories = categories;
        this.seller = seller;
    }
}
