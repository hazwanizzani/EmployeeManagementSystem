package com.project.manager.service;

import com.project.manager.exceptions.NoLeaveApplicationExistsException;
import com.project.manager.model.Employee;
import com.project.manager.model.LeaveApplication;
import com.project.manager.repository.EmployeeRepository;
import com.project.manager.repository.LeaveApplicationRepository;
import com.project.manager.repository.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class LeaveApplicationService {

        private LeaveApplicationRepository leaveApplicationRepository;
        private NotificationService notificationService;
        private EmployeeRepository employeeRepository;

        @Autowired
        public LeaveApplicationService(LeaveApplicationRepository leaveApplicationRepository, NotificationService notificationService, EmployeeRepository employeeRepository)
        {
            this.leaveApplicationRepository = leaveApplicationRepository;
            this.notificationService = notificationService;
            this.employeeRepository = employeeRepository;
        }

        @PostConstruct
        public void init() {
            System.out.println("LeaveApplicationService has been initialized.");
        }

        public Iterable<LeaveApplication> getAllLeaveApplications() {

            return leaveApplicationRepository.findAll();
        }

        public LeaveApplication getLeaveApplicationById(Long leaveApplicationId) {
            return leaveApplicationRepository.findById(leaveApplicationId).orElseThrow(()-> new NoLeaveApplicationExistsException(
                    "No Employee with ID = " + leaveApplicationId));
        }

        public LeaveApplication createLeaveApplication(LeaveApplication leaveApplication) {
            return leaveApplicationRepository.save(leaveApplication);
        }

        public LeaveApplication updateLeaveApplication(Long leaveApplicationId, LeaveApplication leaveApplicationDetails) {
            LeaveApplication existingLeaveApplication = leaveApplicationRepository.findById(leaveApplicationId)
                    .orElseThrow(() -> new NoLeaveApplicationExistsException("No Leave Application with ID = " + leaveApplicationId));

            existingLeaveApplication.setStartDate(leaveApplicationDetails.getStartDate());
            existingLeaveApplication.setEndDate(leaveApplicationDetails.getEndDate());
            existingLeaveApplication.setReason(leaveApplicationDetails.getReason());
            existingLeaveApplication.setStatus(leaveApplicationDetails.getStatus());

            Employee employee = existingLeaveApplication.getEmployee();

            if (existingLeaveApplication.getStatus().equalsIgnoreCase("Approved")) {
                notificationService.emailNotification("\nYour leave on " + existingLeaveApplication.getStartDate() + " has been approved.\n"
                        + "Regards,\nHR Team");
            } else if (existingLeaveApplication.getStatus().equalsIgnoreCase("Rejected")) {
                notificationService.emailNotification("\nDear " + employee.getFullName() + ",\n"
                            + "Your leave o n" + existingLeaveApplication.getStartDate() + " has been rejected.\n"
                            + "Regards,\nHR Team");
            }

            return leaveApplicationRepository.save(existingLeaveApplication);
        }

            public void deleteLeaveApplication (Long leaveApplicationId){
                leaveApplicationRepository.deleteById(leaveApplicationId);
            }

            //customSQL
    public List<LeaveApplication> getLeaveApplicationByEmployeeId(Long id) {
        return leaveApplicationRepository.findByEmployeeId(id);
    }
}
