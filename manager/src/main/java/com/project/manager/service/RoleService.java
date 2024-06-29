package com.project.manager.service;

import com.project.manager.exceptions.NoRoleExistsException;
import com.project.manager.exceptions.RoleAlreadyExistsException;
import com.project.manager.model.Role;
import com.project.manager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RoleService {


    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("RoleService has been initialized.");
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NoRoleExistsException(
                "No Role with ID = " + id));
    }

    public Role createRoles(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role role) {
        Role currentRole = roleRepository.findById(id).orElseThrow(NoRoleExistsException::new);
        currentRole.setName(role.getName());
        return roleRepository.save(currentRole);
    }

    public void deleteRole(Long id) {
        Role newUser = roleRepository.findById(id).orElse(null);
        if (newUser == null) {
            throw new NoRoleExistsException();
        } else {
            roleRepository.deleteById(id);
        }
    }

    public List<Role> getRoleByName(String username) {
        return roleRepository.findByName(username);
    }

}