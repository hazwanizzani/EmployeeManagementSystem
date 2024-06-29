package com.project.manager.controller;

import com.project.manager.model.Employee;
import com.project.manager.model.LeaveApplication;
import com.project.manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByUserId(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/userId/{userId}")
    public List<Employee> getEmployeeByUserId(@PathVariable String userId) {
        return employeeService.getEmployeeByUserId(userId);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<Employee> applyLeave(@PathVariable Long id, @RequestBody LeaveApplication leaveApplication) {
        Employee updatedEmployee = employeeService.addLeaveApplication(id, leaveApplication);
        return ResponseEntity.ok(updatedEmployee);
    }
}
