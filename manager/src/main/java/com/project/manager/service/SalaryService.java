package com.project.manager.service;

import com.project.manager.model.Employee;
import com.project.manager.model.Salary;
import com.project.manager.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> getSalariesByEmployeeAndDate(Employee employee, LocalDate paidDate) {
        return salaryRepository.findByEmployeeAndPaidDate(employee, paidDate);
    }

    public Salary paySalary(Employee employee, LocalDate paidDate, BigDecimal amount) {
        Salary salary = new Salary(employee, paidDate, amount);
        return salaryRepository.save(salary);
    }

}
