package com.cezarybek.ecommerce.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ProductDto {
    private String name;
    private Double price;
    private Integer inStock;
    private Collection<Long> categoryIds;
}
