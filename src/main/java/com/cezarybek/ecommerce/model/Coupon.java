package com.cezarybek.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupon {
    @Id
    private Long id;
    private String couponCode;
    private String discount;
    private Boolean isActive;
}
