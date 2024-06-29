package com.project.manager.repository;

import com.project.manager.model.Employee;
import com.project.manager.model.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Long> {

    List<Salary> findByEmployeeAndPaidDate(Employee employee, LocalDate paidDate);

}
