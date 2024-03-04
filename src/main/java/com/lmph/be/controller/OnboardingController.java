package com.lmph.be.controller;

import com.lmph.be.dto.FlowInfo;
import com.lmph.be.dto.FlowSectionInfo;
import com.lmph.be.dto.SectionInfo;
import com.lmph.be.form.SectionForm;
import com.lmph.be.form.SubsectionForm;
import com.lmph.be.service.FlowService;
import com.lmph.be.service.SectionService;
import com.lmph.be.utility.ControllerUtil;
import com.lmph.be.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("onboarding")
public class OnboardingController {

    private FlowService flowService;

    private SectionService sectionService;

    @Autowired
    public OnboardingController(FlowService flowService, SectionService sectionService) {
        this.flowService = flowService;
        this.sectionService = sectionService;
    }

    /**
     * Onboarding page
     * @return
     */
    @GetMapping("")
    public String onboarding(Model model){
        List<FlowInfo> flowInfos = flowService.retrieveAllFlows();
        model.addAttribute("flows", flowInfos);
        return "onboarding";
    }

    //region Mappings for Section and Subsection

    /**
     * Display Sections and Add Section form
     * @param sectionForm
     * @return
     */
    @SuppressWarnings("unused")
    @GetMapping("/section")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String sectionForm(@ModelAttribute("sectionForm") SectionForm sectionForm) {
        return "section_form";
    }

    /**
     * Edit Section form
     * @param sectionId
     * @param sectionForm
     * @return
     */
    @GetMapping("/section/{sectionId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String sectionForm(@PathVariable Long sectionId, @ModelAttribute("sectionForm") SectionForm sectionForm) {
        sectionForm.setSectionId(sectionId);
        return "section_form";
    }

    /**
     * Add Subsection form
     * @param sectionId
     * @param subsectionForm
     * @return
     */
    @GetMapping("/section/{sectionId}/subsection")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String subsectionForm(@PathVariable Long sectionId, @ModelAttribute("subsectionForm") SubsectionForm subsectionForm) {
        subsectionForm.setSectionId(sectionId);
        return "subsection_form";
    }

    /**
     * Edit Subsection form
     * @param sectionId
     * @param subsectionId
     * @param subsectionForm
     * @return
     */
    @GetMapping("/section/{sectionId}/subsection/{subsectionId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String subsectionForm(@PathVariable Long sectionId, @PathVariable Long subsectionId, @ModelAttribute("subsectionForm") SubsectionForm subsectionForm) {
        subsectionForm.setSectionId(sectionId);
        subsectionForm.setSubsectionId(subsectionId);
        return "subsection_form";
    }

    //endregion

    //region Mappings for Flow

    @RequestMapping("/flow")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String flowManagement(Model model){
        List<FlowInfo> flowInfos = flowService.retrieveAllFlows();
        model.addAttribute("flows", flowInfos);
        return "flow_list";
    }

    @RequestMapping("/flow/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String flowManagementCreate(Model model){
        List<SectionInfo> sections = sectionService.getAllSections();
        model.addAttribute("sectionsList", sections);
        model.addAttribute("flowInfo", new FlowInfo());
        return "flow_form";
    }

    @RequestMapping("/flow/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String retrieveFlowAndSectionsById(@PathVariable("id") Long flowId,
                                              Model model){
        FlowInfo flowInfo = this.flowService.getFlowAndItsSections(flowId);

        flowInfo.setFlowSectionInfos(
                flowInfo.getFlowSectionInfos()
                        .stream()
                        .sorted(Comparator.comparing(FlowSectionInfo::getSortOrder)).toList());

        List<SectionInfo> sections = sectionService.getAllSections();

        model.addAttribute("sectionsList", sections);
        model.addAttribute("flowInfo", flowInfo);

        return "flow_form";
    }

    @PostMapping("/flow/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String persistFlowAndSections(@ModelAttribute FlowInfo form,
                                         RedirectAttributes redirectAttributes){
        String action = form.getFlowId() != null ? " added!":"updated!";

        if(form != null) {
            form.setCreatedBy(SecurityUtil.getCurrentUser());
            try{
                this.flowService.upsertFlow(form);
                redirectAttributes.addFlashAttribute("customMessage", form.getName() + " successfully "+ action);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return ControllerUtil.showInternalErrorMessage(redirectAttributes, ex.getMessage(), "/onboarding");
            }
        }

        return "redirect:/onboarding";
    }

    @RequestMapping("/flow/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String flowManagementDelete(@PathVariable Long id,
                                       @RequestParam("name") String name,
                                       RedirectAttributes redirectAttributes){
        try {
            flowService.deleteFlow(id);
            redirectAttributes.addFlashAttribute("customMessage", name + " successfully deleted!");
        } catch (Exception ex) {
            return ControllerUtil.showInternalErrorMessage(redirectAttributes, ex.getMessage(), "/onboarding");
        }
        return "redirect:/onboarding";
    }

    //endregion
}
