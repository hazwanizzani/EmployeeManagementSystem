package com.project.manager.repository;

import com.project.manager.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    //CustomSQL
    List<Employee> findByUserId(String userId);
}

