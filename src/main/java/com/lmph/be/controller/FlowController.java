package com.lmph.be.controller;

import com.lmph.be.dto.FlowAndSectionsInfo;
import com.lmph.be.dto.FlowInfo;
import com.lmph.be.dto.FlowSectionInfo;
import com.lmph.be.dto.SectionInfo;
import com.lmph.be.entity.Flow;
import com.lmph.be.entity.FlowSection;
import com.lmph.be.form.FlowAndSectionsForm;
import com.lmph.be.form.FlowForm;
import com.lmph.be.form.FlowSectionForm;
import com.lmph.be.service.FlowService;
import com.lmph.be.service.SectionService;
import com.lmph.be.utility.ControllerUtil;
import com.lmph.be.utility.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
public class FlowController {

    FlowService flowService;

    SectionService sectionService;

    @Autowired
    public FlowController(FlowService flowService, SectionService sectionService) {
        this.flowService = flowService;
        this.sectionService = sectionService;
    }


    //region Mutation Mappings for GraphQL

    @QueryMapping
    public List<FlowInfo> allFlows(){
        return this.flowService.retrieveAllFlows();
    }

    @QueryMapping
    public List<FlowSection> allFlowsSections(){
        return this.flowService.retrieveAllFlowSections();
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FlowInfo upsertFlow(@Valid @Argument FlowForm form){
        try{
            if (form != null) {

                //populate these required fields
                form.setCreatedBy(SecurityUtil.getCurrentUser());

                if(form.getFlowId() == null)
                    form.setCreatedDate(LocalDate.now());

                return this.flowService.upsertFlow(form);
            }
            else
                throw new Exception("Flow is null.");
        } catch(Exception e){
            return null;
        }
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean deleteFlow(@Valid @Argument Long flowId){
        try {
            if(flowId != null)
                this.flowService.deleteFlow(flowId);
            else
                throw new Exception("Flow ID is null.");

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public FlowSectionInfo upsertFlowSection(@Valid @Argument FlowSectionForm form){
        try{
            if(form != null)
                return this.flowService.upsertFlowSection(form);
            else
                throw new Exception("Employee Flow is null.");
        }
        catch(Exception ex){
            return null;
        }
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean deleteFlowSection(@Argument Long flowSectionId){
        try {
            if(flowSectionId != null)
                this.flowService.deleteFlowSection(flowSectionId);
            else
                throw new Exception("Flow Section ID is null.");

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //endregion

}
