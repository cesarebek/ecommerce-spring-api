package com.cezarybek.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Category {
    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
