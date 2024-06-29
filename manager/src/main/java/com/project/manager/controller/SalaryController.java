package com.project.manager.controller;

import com.project.manager.model.Employee;
import com.project.manager.model.Salary;
import com.project.manager.service.EmployeeService;
import com.project.manager.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/salaries")
public class SalaryController {

    private SalaryService salaryService;

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setSalaryService(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Salary>> getSalariesByEmployeeAndDate(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paidDate
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Salary> salaries = salaryService.getSalariesByEmployeeAndDate(employee, paidDate);
        return ResponseEntity.ok(salaries);
    }

    @PostMapping("/employee/{employeeId}/pay")
    public ResponseEntity<Salary> paySalary(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paidDate,
            @RequestParam BigDecimal amount
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Salary salary = salaryService.paySalary(employee, paidDate, amount);
        return ResponseEntity.ok(salary);
    }
}
