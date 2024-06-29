package com.project.manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salaries")
@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "paid_date", nullable = false)
    private LocalDate paidDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public Salary(Employee employee, LocalDate paidDate, BigDecimal amount) {
        this.employee = employee;
        this.paidDate = paidDate;
        this.amount = amount;
    }
}
