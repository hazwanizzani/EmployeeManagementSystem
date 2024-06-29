package com.project.manager.repository;

import com.project.manager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> getByUsername (String username);

    User findByUsername(String username);
}
