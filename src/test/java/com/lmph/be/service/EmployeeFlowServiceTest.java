package com.lmph.be.service;


import com.lmph.be.dto.EmployeeFlowInfo;
import com.lmph.be.form.EmployeeFlowForm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class EmployeeFlowServiceTest {

    @Autowired
    private EmployeeFlowService employeeFlowService;

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    void getEmployeeFlowsByEmployeeId() {
        List<EmployeeFlowInfo> employeeFlowInfos = this.employeeFlowService.getEmployeeFlowsByEmployeeId(1L);
        assert employeeFlowInfos != null;
        assert !employeeFlowInfos.isEmpty();
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    void upsertEmployeeFlowOk() {
        EmployeeFlowForm employeeFlowForm = new EmployeeFlowForm();
        employeeFlowForm.setEmployeeId(1L);
        employeeFlowForm.setFlowId(1L);
        employeeFlowForm.setId(1L);
        employeeFlowForm.setSortOrder(1);

        EmployeeFlowInfo employeeFlowInfo = this.employeeFlowService.upsertEmployeeFlow(employeeFlowForm);

        assert employeeFlowInfo != null;
        assert employeeFlowInfo.getId() != null;
        assert employeeFlowInfo.getEmployee().getId() == 1L;
        assert employeeFlowInfo.getSortOrder() == 1;
    }

//    @Test
//    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
//    void upsertEmployeeFlowsOk() {
//
//        List<EmployeeFlowInfo> employeeFlowInfos = new ArrayList<>();
//
//
//
//        this.employeeFlowService.upsertEmployeeFlows(employeeFlowInfos);
//
//        List<EmployeeFlowInfo> infos = this.employeeFlowService.getEmployeeFlowsByEmployeeId(1L);
//
//        assert infos != null;
//        assert !infos.isEmpty();
//    }
//
//    @Test
//    void deleteEmployeeFlowOk() {
//    }
}
