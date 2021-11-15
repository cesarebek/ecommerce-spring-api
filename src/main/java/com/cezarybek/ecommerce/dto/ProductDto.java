package com.cezarybek.ecommerce.dto;

import com.cezarybek.ecommerce.model.Category;
import com.cezarybek.ecommerce.model.Seller;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String productName;
    private Integer inStock;
    private List<Category> categoryList;
    private Seller seller;
}
