package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.model.Role;
import com.cezarybek.ecommerce.service.RoleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public List<Role> allCategories() {
        return roleService.getAllRoles();
    }

    @PostMapping("/save")
    public Role addCategory(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping("/{roleId}")
    public Role getCategory(@PathVariable long roleId) throws NotFoundException {
        return roleService.getRoleById(roleId);
    }

    @PostMapping("/{roleId}/privilege/{privilegeId}")
    public String addPrivilegeToRole(@PathVariable final long roleId, @PathVariable final long privilegeId) throws NotFoundException {
        return roleService.addPrivilegeToRole(roleId, privilegeId);
    }
}
