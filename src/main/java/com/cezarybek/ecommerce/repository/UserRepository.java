package com.cezarybek.ecommerce.repository;

import com.cezarybek.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface UserRepository extends JpaRepository<User, Long> {
}
