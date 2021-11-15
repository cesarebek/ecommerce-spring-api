package com.cezarybek.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Seller {
    @Id
    private Double id;
    private String sellerName;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
}
