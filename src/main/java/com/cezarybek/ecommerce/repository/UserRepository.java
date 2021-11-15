package com.cezarybek.ecommerce.repository;

import com.cezarybek.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
