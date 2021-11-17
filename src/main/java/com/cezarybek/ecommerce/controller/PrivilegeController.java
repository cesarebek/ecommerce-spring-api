package com.cezarybek.ecommerce.controller;

import com.cezarybek.ecommerce.model.Privilege;
import com.cezarybek.ecommerce.service.PrivilegeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/privilege")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping("/all")
    public List<Privilege> allPrivileges() {
        return privilegeService.getAllPrivileges();
    }

    @PostMapping("/save")
    public Privilege addPrivilege(@RequestBody Privilege privilege) {
        return privilegeService.savePrivilege(privilege);
    }

    @GetMapping("/{privilegeId}")
    public Privilege getPrivilege(@PathVariable long privilegeId) throws NotFoundException {
        return privilegeService.getPrivilegeById(privilegeId);
    }
}
