package com.cezarybek.ecommerce.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String privilegeName;
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
