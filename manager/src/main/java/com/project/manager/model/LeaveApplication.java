package com.project.manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="leave")
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leaveApplicationId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Date startDate;
    private Date endDate;
    private String status;
    private String reason;


}
