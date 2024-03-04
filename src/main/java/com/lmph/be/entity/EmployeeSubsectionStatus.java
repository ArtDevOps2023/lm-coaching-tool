package com.lmph.be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="employee_subsection_status")
public class EmployeeSubsectionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Column(name = "employee_id", insertable = false, updatable = false)
    private Long employeeId;

    @JoinColumn(name = "subsection_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subsection subsection;

    private String status;

    private LocalDate startDate;

    private LocalDate completedDate;

    @Override
    public String toString() {
        return "EmployeeSubsectionStatus{" +
                "id=" + id +
                ", employee=" + employee.getLastName() +
                ", subsection=" + subsection.getDescription() +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", completedDate=" + completedDate +
                '}';
    }
}
