package com.cezarybek.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Integer inStock;
    private Collection<Long> categoryIds;

    public ProductDto(String name, Double price, Integer inStock, Collection<Long> categoryIds) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.categoryIds = categoryIds;
    }
}
