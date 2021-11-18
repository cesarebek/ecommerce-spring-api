package com.cezarybek.ecommerce.service;

import com.cezarybek.ecommerce.exception.EcommerceException;
import com.cezarybek.ecommerce.model.Privilege;
import com.cezarybek.ecommerce.model.Role;
import com.cezarybek.ecommerce.repository.PrivilegeRepository;
import com.cezarybek.ecommerce.repository.RoleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.sun.xml.bind.v2.schemagen.Util.equalsIgnoreCase;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Role saveRole(Role role) {
        Optional<Role> roleExist = roleRepository.findByName(role.getName());
        if (roleExist.isPresent())
            throw new EcommerceException(String.format("Role %s already exist!", roleExist.get().getName()), HttpStatus.BAD_REQUEST);
        if (!equalsIgnoreCase(role.getName().substring(0, 5), "ROLE_"))
            throw new EcommerceException("Role name must start with ROLE_", HttpStatus.BAD_REQUEST);
        role.setName(role.getName().toUpperCase(Locale.ROOT));
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long roleId) throws NotFoundException {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isEmpty()) throw new NotFoundException("Role not found");
        return role.get();
    }

    @Transactional
    public String addPrivilegeToRole(long roleId, long privilegeId) throws NotFoundException {
        Optional<Role> role = roleRepository.findById(roleId);
        Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
        if (role.isEmpty() || privilege.isEmpty()) throw new NotFoundException("Role or Privilege not found!");
        role.get().getPrivileges().add(privilege.get());
        return String.format("Privilege %s successfully added to role %s.", privilege.get().getName(), role.get().getName());
    }
}
