package com.lmph.be.dto;

import com.lmph.be.entity.Employee;
import com.lmph.be.entity.Subsection;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeSubsectionStatusInfo {

    private Long id;

    private Employee employee;

    private Subsection subsection;

    private String status;

    private LocalDate startDate;

    private LocalDate completedDate;
}
