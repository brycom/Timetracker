package com.TimetrackerBackend.TimetrackerBackend.contollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TimetrackerBackend.TimetrackerBackend.models.Role;
import com.TimetrackerBackend.TimetrackerBackend.services.RoleService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private final RoleService roleService;

    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/role")
    public String createRole(@RequestBody Role role) {
        roleService.createRole(role);
        return "Role created";
    }

    @GetMapping("/role")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

}
