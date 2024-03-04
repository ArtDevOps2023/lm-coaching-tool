package com.lmph.be.utility;

import com.lmph.be.dto.*;
import com.lmph.be.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.Comparator;

public class DTOUtil {

    /**
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param flow
     * @return
     */
    public static FlowAndSectionsInfo toFlowAndSectionsInfo(Flow flow) {
        FlowAndSectionsInfo flowAndSectionsInfo = new FlowAndSectionsInfo();
        flowAndSectionsInfo.setFlowId(flow.getFlowId());
        flowAndSectionsInfo.setFlowName(flow.getName());

        flowAndSectionsInfo.setSectionInfos(
                flow.getFlowSections().stream().map(DTOUtil::toFlowSectionInfo)
                        .sorted(Comparator.comparing(FlowSectionInfo::getSortOrder)).toList());

        return flowAndSectionsInfo;
    }

    /**
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param flowSection
     * @return
     */
    public static FlowSectionInfo toFlowSectionInfo(FlowSection flowSection){
        FlowSectionInfo flowSectionInfo = new FlowSectionInfo();
        flowSectionInfo.setId(flowSection.getId());

        if(flowSection.getFlow()!=null)
            flowSectionInfo.setFlowInfo(DTOUtil.toFlowInfoNullifyFlow(flowSection.getFlow()));

        flowSectionInfo.setSectionInfo(DTOUtil.toSectionInfo(flowSection.getSection()));
        flowSectionInfo.setSortOrder(flowSection.getSortOrder());
        return flowSectionInfo;
    }

    /**
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param flowSection
     * @return
     */
    public static FlowSectionInfo toFlowSectionInfoNullifyFlow(FlowSection flowSection){
        FlowSectionInfo flowSectionInfo = new FlowSectionInfo();
        flowSectionInfo.setId(flowSection.getId());
        flowSectionInfo.setFlowInfo(null);
        flowSectionInfo.setSectionInfo(DTOUtil.toSectionInfo(flowSection.getSection()));
        flowSectionInfo.setSortOrder(flowSection.getSortOrder());
        return flowSectionInfo;
    }

    /**
     *
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @param section
     * @return
     */
    public static SectionInfo toSectionInfo(Section section){
        SectionInfo sectionInfo = new SectionInfo();

        sectionInfo.setSectionId(section.getSectionId());
        sectionInfo.setName(section.getName());
        sectionInfo.setColor(section.getColor());
        sectionInfo.setSubsectionList(null);

        return sectionInfo;
    }

    public static EmployeeFlowInfo toEmployeeFlowInfo(EmployeeFlow employeeFlow) {
        EmployeeFlowInfo employeeFlowInfo = new EmployeeFlowInfo();

        BeanUtils.copyProperties(employeeFlow, employeeFlowInfo);

        return employeeFlowInfo;
    }

    public static EmployeeFlow employeeInfotoEmployeeFlow(EmployeeFlowInfo employeeFlowInfo) {
        EmployeeFlow employeeFlow = new EmployeeFlow();

        employeeFlow.setId(employeeFlowInfo.getId());
        employeeFlow.setEmployee(new Employee(employeeFlowInfo.getEmployee().getId()));
        employeeFlow.setFlow(new Flow(employeeFlowInfo.getFlow().getFlowId()));
        employeeFlow.setSortOrder(employeeFlowInfo.getSortOrder());

        return employeeFlow;
    }

    /**
     * @author Jeffrey John Javison
     * @since 12-Dec-2023
     * @param flow
     * @return
     */
    public static FlowInfo toFlowInfo(Flow flow){
        FlowInfo flowInfo = new FlowInfo();

        flowInfo.setFlowId(flow.getFlowId());
        flowInfo.setName(flow.getName());
        flowInfo.setCreatedBy(flow.getCreatedBy());
        flowInfo.setCreatedDate(flow.getCreatedDate());
        flowInfo.setFlowSectionInfos(flow.getFlowSections().stream().map(DTOUtil::toFlowSectionInfo).toList());

        return flowInfo;
    }

    public static FlowInfo toFlowInfoNullifyFlow(Flow flow){
        FlowInfo flowInfo = new FlowInfo();

        flowInfo.setFlowId(flow.getFlowId());
        flowInfo.setName(flow.getName());
        flowInfo.setFlowSectionInfos(flow.getFlowSections().stream().map(DTOUtil::toFlowSectionInfoNullifyFlow).toList());
        flowInfo.setCreatedBy(flow.getCreatedBy());
        flowInfo.setCreatedDate(flow.getCreatedDate());

        return flowInfo;
    }

    public static EmployeeSubsectionStatusInfo fromEntityToInfo(EmployeeSubsectionStatus entity){
        EmployeeSubsectionStatusInfo info = new EmployeeSubsectionStatusInfo();

        BeanUtils.copyProperties(entity, info);

        return info;
    }

}
