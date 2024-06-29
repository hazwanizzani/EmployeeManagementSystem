package com.project.manager.controller;

import com.project.manager.model.Role;
import com.project.manager.service.RoleService;
import com.project.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {

        this.roleService = roleService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<Role> getAllRoles() {

        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {

        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRoles(@RequestBody Role role) {

        return roleService.createRoles(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role roles) {
        return roleService.updateRole(id, roles);
    }

    @GetMapping("/roles/{roleName}")
    public List<Role> getUserByUsername(@PathVariable String roleName) {
        return roleService.getRoleByName(roleName);
    }

}
