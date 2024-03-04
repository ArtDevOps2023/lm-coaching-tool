package com.lmph.be.utility;

import com.lmph.be.dto.FlowSectionInfo;
import com.lmph.be.entity.*;
import com.lmph.be.form.EmployeeFlowForm;
import com.lmph.be.form.EmployeeSubsectionStatusForm;
import com.lmph.be.form.FlowForm;
import org.springframework.beans.BeanUtils;

public class FormUtil {

    /**
     *
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param employeeFlowForm
     * @return
     */
    public static EmployeeFlow employeeFlowFormToEntity(EmployeeFlowForm employeeFlowForm){
        EmployeeFlow employeeFlow = new EmployeeFlow();

        employeeFlow.setId(employeeFlowForm.getId());
        employeeFlow.setEmployee(new Employee(employeeFlowForm.getEmployeeId()));
//        if(employeeFlowForm.getFlows() != null)
//            employeeFlow.setFlows(employeeFlowForm.getFlows().stream().map(FormUtil::flowFormToEntity).toList());
//        else if(employeeFlowForm.getFlowId() != null)
//            employeeFlow.getFlows().add(new Flow(employeeFlowForm.getFlowId()));
        employeeFlow.setFlow(new Flow(employeeFlowForm.getFlowId()));

        employeeFlow.setSortOrder(employeeFlowForm.getSortOrder());

        return employeeFlow;

    }

    /**
     * @author Jeffrey John Javison
     * @since 12-Dec-2023
     * @param flowForm
     * @return
     */
    public static Flow flowFormToEntity(FlowForm flowForm){
        Flow flow = new Flow();

        BeanUtils.copyProperties(flowForm, flow);

        return flow;
    }

    public static FlowForm toFlowForm(Flow flow) {
        FlowForm form = new FlowForm();

        BeanUtils.copyProperties(flow, form);

        return form;
    }

    public static EmployeeSubsectionStatus fromFormToEntity(EmployeeSubsectionStatusForm form){
        EmployeeSubsectionStatus employeeSubsectionStatus = new EmployeeSubsectionStatus();

        employeeSubsectionStatus.setId(form.getId());
        employeeSubsectionStatus.setEmployee(new Employee(form.getEmployeeId()));
        employeeSubsectionStatus.setSubsection(new Subsection(form.getSubsectionId()));
        employeeSubsectionStatus.setStatus(form.getStatus());
        employeeSubsectionStatus.setStartDate(form.getStartDate());
        employeeSubsectionStatus.setCompletedDate(form.getCompletedDate());

        return employeeSubsectionStatus;
    }

}
