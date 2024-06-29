package com.project.manager.service;

import com.project.manager.exceptions.NoEmployeeExistsException;
import com.project.manager.model.Employee;
import com.project.manager.model.LeaveApplication;
import com.project.manager.repository.EmployeeRepository;
import com.project.manager.repository.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;
    private NotificationService notificationService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, NotificationService notificationService)
    {
        this.employeeRepository = employeeRepository;
        this.notificationService = notificationService;
    }

    @PostConstruct
    public void init() {
        System.out.println("EmployeeService has been initialized.");
    }

    @Transactional
    public Employee addLeaveApplication(Long id, LeaveApplication leaveApplication) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        leaveApplication.setEmployee(employee);
        employee.getLeaveApplications().add(leaveApplication);

        return employeeRepository.save(employee);
    }

    public Iterable<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new NoEmployeeExistsException(
                "No Employee with ID = " + id));
    }

    public Employee createEmployee(Employee employee) {
        notificationService.emailNotification("\nDear " + employee.getFullName() + ",\n"
                + "Your ID has been created, Username: " + employee.getUserId() + " & Password: " + employee.getPassword() + ".\n"
                + "Regards,\nHR Team");
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(NoEmployeeExistsException::new);
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPayroll(employee.getPayroll());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setUserId(employee.getUserId());
        existingEmployee.setPassword(employee.getPassword());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee newStudent = employeeRepository.findById(id).orElse(null);
        if (newStudent == null){
            throw new NoEmployeeExistsException();
        }
        else {
            employeeRepository.deleteById(id);
        }
    }
    //customSQL
    public List<Employee> getEmployeeByUserId(String userId) {
        return employeeRepository.findByUserId(userId);
    }

}