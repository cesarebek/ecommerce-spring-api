package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.model.Privilege;
import com.cezarybek.ecommerce.repository.PrivilegeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Privilege savePrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    public Privilege getPrivilegeById(long privilegeId) throws NotFoundException {
        Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
        if (privilege.isEmpty()) throw new NotFoundException("Privilege not found");
        return privilege.get();
    }


}
