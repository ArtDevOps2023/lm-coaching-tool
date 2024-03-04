package com.lmph.be.dto;

import com.lmph.be.entity.Employee;
import com.lmph.be.entity.Flow;
import lombok.Data;

@Data
public class EmployeeFlowInfo {

    private Long id;

    private Employee employee;

    private Flow flow;

    private Integer sortOrder;

}
