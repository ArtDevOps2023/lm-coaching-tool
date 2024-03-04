package com.lmph.be.service;

import com.lmph.be.dto.EmployeeSubsectionStatusInfo;
import com.lmph.be.entity.EmployeeSubsectionStatus;
import com.lmph.be.form.EmployeeSubsectionStatusForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class EmployeeSubsectionStatusServiceTest {

    @Autowired
    private EmployeeSubsectionStatusService service;

    @Test
    @Sql(scripts = {"classpath:sql/truncateForEmployeeSubsectionStatus.sql", "classpath:sql/insertForEmployeeSubsectionStatus.sql"})
    public void retrieveAllEmployeeSubsectionStatusOk(){
        List<EmployeeSubsectionStatusInfo> list = this.service.retrieveAllEmployeeSubsectionStatus();
        assert list != null;
        assert !list.isEmpty();
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForEmployeeSubsectionStatus.sql", "classpath:sql/insertForEmployeeSubsectionStatus.sql"})
    public void retrieveEmployeeSubsectionStatusByEmployeeIdOk(){
        EmployeeSubsectionStatusForm form = new EmployeeSubsectionStatusForm();

        form.setId(1L);
        form.setEmployeeId(1L);
        form.setSubsectionId(1L);
        form.setStatus("Done");
        form.setStartDate(LocalDate.now());
        form.setCompletedDate(LocalDate.now().plusDays(1L));

        EmployeeSubsectionStatusInfo info = this.service.upsertEmployeeSubsectionStatus(form);
        assert info != null;
        assert info.getSubsection().getSubsectionId() == 1;
        assert info.getCompletedDate().isAfter(info.getStartDate());
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForEmployeeSubsectionStatus.sql", "classpath:sql/insertForEmployeeSubsectionStatus.sql"})
    public void upsertEmployeeSubsectionStatusOk(){
        List<EmployeeSubsectionStatusInfo> list = this.service.retrieveAllEmployeeSubsectionStatus();
        assert list != null;
        assert !list.isEmpty();
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForEmployeeSubsectionStatus.sql", "classpath:sql/insertForEmployeeSubsectionStatus.sql"})
    public void deleteEmployeeSubsectionStatusOk(){
        this.service.deleteEmployeeSubsectionStatus(1L);
        List<EmployeeSubsectionStatusInfo> list = this.service.retrieveEmployeeSubsectionStatusByEmployeeId(1L);
        assert list != null;
        assert list.isEmpty();
    }


}
