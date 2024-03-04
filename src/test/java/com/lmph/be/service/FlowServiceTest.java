package com.lmph.be.service;

import com.lmph.be.dto.FlowInfo;
import com.lmph.be.dto.FlowSectionInfo;
import com.lmph.be.entity.FlowSection;
import com.lmph.be.form.FlowForm;
import com.lmph.be.form.FlowSectionForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class FlowServiceTest {

    @Autowired
    private FlowService flowService;

    @Test
    @Sql("classpath:sql/truncateForFlow.sql")
    public void flowReturnsNull (){
        List<FlowInfo> flowList = this.flowService.retrieveAllFlows();
        assert flowList.isEmpty();
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void flowReturnsOne (){
        List<FlowInfo> flowList = this.flowService.retrieveAllFlows();
        assert flowList.size() == 1;
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void updateFlowInfoOk (){
        FlowInfo flowInfo = new FlowInfo();
        flowInfo.setFlowId(1L);
        flowInfo.setName("Technical");
        flowInfo.setCreatedBy("New Admin");
        flowInfo.setCreatedDate(LocalDate.now());

        FlowInfo updatedFlow = this.flowService.upsertFlow(flowInfo);

        assert !updatedFlow.getName().equals("Generic");
        assert updatedFlow.getCreatedBy().equals("New Admin");
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void updateFlowFormOk (){
        FlowForm flowForm = new FlowForm();
        flowForm.setFlowId(1L);
        flowForm.setName("Technical");
        flowForm.setCreatedBy("New Admin");
        flowForm.setCreatedDate(LocalDate.now());

        FlowInfo updatedFlow = this.flowService.upsertFlow(flowForm);

        assert !updatedFlow.getName().equals("Generic");
        assert updatedFlow.getCreatedBy().equals("New Admin");
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void deleteFlowOk (){
        this.flowService.deleteFlow(1L);
        assert this.flowService.retrieveAllFlows().isEmpty();
    }

    @Test
    @Sql("classpath:sql/truncateForFlow.sql")
    public void flowSectionReturnsNull (){
        List<FlowSection> flowSections = this.flowService.retrieveAllFlowSections();
        assert flowSections.isEmpty();
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void flowSectionReturnsOne (){
        List<FlowSection> flowSections = this.flowService.retrieveAllFlowSections();
        assert flowSections.size() == 1;
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void updateFlowSectionInfoOk (){

        FlowSectionForm flowSectionForm = new FlowSectionForm();
        flowSectionForm.setId(1L);
        flowSectionForm.setFlowId(1L);
        flowSectionForm.setSectionId(1L);
        flowSectionForm.setSortOrder(1);

        FlowSectionInfo flowSectionInfo = this.flowService.upsertFlowSection(flowSectionForm);

        assert flowSectionInfo != null;
        assert flowSectionInfo.getId() == 1L;
        assert flowSectionInfo.getFlowInfo().getFlowId() == 1L;
        assert flowSectionInfo.getSectionInfo().getSectionId() == 1L;
        assert flowSectionInfo.getSortOrder() == 1L;
    }

    @Test
    @Sql(scripts = {"classpath:sql/truncateForFlow.sql", "classpath:sql/insertForFlow.sql"})
    public void deleteFlowSectionOk (){
        this.flowService.deleteFlowSection(1L);
        assert this.flowService.retrieveAllFlowSections().isEmpty();
    }

}
