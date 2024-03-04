package com.lmph.be.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeSubsectionStatusForm {

    private Long id;
    
    private Long employeeId;

    private Long subsectionId;

    private String status;

    private LocalDate startDate;

    private LocalDate completedDate;

}
