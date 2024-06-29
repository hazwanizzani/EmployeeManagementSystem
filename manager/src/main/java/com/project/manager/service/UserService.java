package com.project.manager.service;

import com.project.manager.exceptions.NoUserExistsException;
import com.project.manager.exceptions.UserAlreadyExistException;
import com.project.manager.model.Role;
import com.project.manager.model.User;
import com.project.manager.repository.RoleRepository;
import com.project.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("UserService has been initialized.");
    }

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long userID) {
        if (userID == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        return userRepository.findById(userID)
                .orElseThrow(() -> new NoUserExistsException("No User with ID = " + userID));
    }


    public User createUser(User user) {
            return userRepository.save(user);
    }

    public User updateUser(Long userID, User user) {
        User existUser = userRepository.findById(userID).orElseThrow(NoUserExistsException::new);
        existUser.setUsername(user.getUsername());
        existUser.setPassword(user.getPassword());
        existUser.setEmail(user.getEmail());
        return userRepository.save(existUser);
    }

    public void deleteUser(Long userID) {
        User newUser = userRepository.findById(userID).orElse(null);
        if (newUser == null) {
            throw new NoUserExistsException();
        } else {
            userRepository.deleteById(userID);
        }
    }

    public List<User> getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }


    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}