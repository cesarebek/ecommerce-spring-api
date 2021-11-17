package com.cezarybek.ecommerce.repository;

import com.cezarybek.ecommerce.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
//    Optional<Privilege> findByName(String privilegeName);
}
