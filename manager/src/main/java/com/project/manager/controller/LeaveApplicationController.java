package com.project.manager.controller;

import com.project.manager.model.LeaveApplication;
import com.project.manager.service.EmployeeService;
import com.project.manager.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveApplicationController {

    @Autowired
    private LeaveApplicationService leaveApplicationService;
    private EmployeeService employeeService;

    public void setLeaveApplicationService(LeaveApplicationService leaveApplicationService, EmployeeService employeeService) {
        this.leaveApplicationService = leaveApplicationService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationService.getAllLeaveApplications();
    }
    @GetMapping("/{leaveApplicationId}")
    public LeaveApplication getLeaveApplicationById(@PathVariable Long leaveApplicationId) {
        return leaveApplicationService.getLeaveApplicationById(leaveApplicationId);
    }

    @GetMapping("/employee/{id}")
    public List<LeaveApplication> getLeaveApplicationByEmployeeId(@PathVariable Long id) {
        return leaveApplicationService.getLeaveApplicationByEmployeeId(id);
    }

    @PostMapping
    public LeaveApplication createLeaveApplication(@RequestBody LeaveApplication leaveApplication) {
        return leaveApplicationService.createLeaveApplication(leaveApplication);
    }


    @PutMapping("/{leaveApplicationId}")
    public LeaveApplication updateLeaveApplication(@PathVariable Long leaveApplicationId, @RequestBody LeaveApplication leaveApplicationDetails) {
        return leaveApplicationService.updateLeaveApplication(leaveApplicationId, leaveApplicationDetails);
    }

}

