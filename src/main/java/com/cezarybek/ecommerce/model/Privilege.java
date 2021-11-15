package com.cezarybek.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Privilege {
    @Id
    @GeneratedValue
    private Long id;
    private String privilegeName;
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
