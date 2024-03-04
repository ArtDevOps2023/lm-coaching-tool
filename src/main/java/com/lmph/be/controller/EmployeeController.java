package com.lmph.be.controller;

import java.util.List;

import com.lmph.be.dto.EmployeeFlowInfo;
import com.lmph.be.dto.EmployeeSubsectionStatusInfo;
import com.lmph.be.dto.FlowInfo;
import com.lmph.be.entity.EmployeeFlow;
import com.lmph.be.entity.EmployeeSubsectionStatus;
import com.lmph.be.form.EmployeeFlowForm;
import com.lmph.be.form.EmployeeSubsectionStatusForm;
import com.lmph.be.service.EmployeeFlowService;
import com.lmph.be.service.EmployeeSubsectionStatusService;
import com.lmph.be.service.FlowService;
import com.lmph.be.utility.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;


import com.lmph.be.dto.EmployeeInfo;
import com.lmph.be.form.EmployeeForm;
import com.lmph.be.service.EmployeeService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User Controller class
 * @author Jhun Tiballa
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeFlowService employeeFlowService;

	@Autowired
	private FlowService flowService;

	@Autowired
	private EmployeeSubsectionStatusService employeeSubsectionStatusService;

	/**
	 * GraphQL controller for fetching single employee
	 * @param id
	 * @return
	 */
	@QueryMapping
	public EmployeeInfo employeeById(@Argument Long id) {
		return this.employeeService.getEmployee( id );			
	}
		
	/**
	 * GraphQL for fetching all employees
	 * @return
	 */
	@QueryMapping
	public List<EmployeeInfo> employees() {
		return this.employeeService.getEmployees();
	}
	
	@MutationMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public EmployeeInfo upsertEmployee(@Valid @Argument EmployeeForm form) throws ConstraintViolationException {
		return this.employeeService.upsert(form);	
	}
	
	@MutationMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Boolean deleteEmployee(@Argument Long id) {
		
		this.employeeService.delete(id);
		
		return true;
	}

	@RequestMapping("/employee/flow/view/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String viewEmployeeFlow(@PathVariable("id") Long empId, Model model){
		List<EmployeeFlowInfo> employeeFlows= this.employeeFlowsById(empId);
		model.addAttribute("empFlows", employeeFlows);
		return "employee_flow_view";
	}

	@RequestMapping("/employee/flow/configure/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String configureEmployeeFlow(@PathVariable("id") Long empId, Model model){
		EmployeeInfo employeeInfo = this.employeeService.getEmployee(empId);
		List<FlowInfo> flowInfos = this.flowService.retrieveAllFlows();
		model.addAttribute("emp", employeeInfo);
		model.addAttribute("flowInfos", flowInfos);
		return "employee_flow_configure";
	}

	@PostMapping("/employee/flow/configure/save")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String saveEmployeeFlow(@ModelAttribute EmployeeInfo employeeInfo,
								   RedirectAttributes redirectAttributes){

		try {
			if(employeeInfo.getId() != null && employeeInfo.getEmployeeFlowInfos() != null) {
				this.employeeFlowService.deleteEmployeeFlow(employeeInfo.getId());
				this.employeeFlowService.upsertEmployeeFlows(employeeInfo.getEmployeeFlowInfos());
			}

			redirectAttributes.addFlashAttribute("customMessage",
					"Onboarding Flow for "+ employeeInfo.getFullName() +" successfully configured.");

		} catch (Exception ex) {
			return ControllerUtil.showInternalErrorMessage(redirectAttributes, ex.getMessage(), "/employees");
		}

		return "redirect:/employees";
	};

	//TODO JJJ: create javadoc
	@QueryMapping
	public List<EmployeeFlowInfo> employeeFlowsById(@Argument Long id){
		return this.employeeFlowService.getEmployeeFlowsByEmployeeId(id);
	}

	@MutationMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public EmployeeFlowInfo upsertEmployeeFlow(@Valid @Argument EmployeeFlowForm form){
		try{
			if(form != null)
				return this.employeeFlowService.upsertEmployeeFlow(form);
			else
				throw new Exception("Employee Flow is null.");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}


	@MutationMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Boolean deleteEmployeeFlow(@Valid @Argument Long employeeFlowId){
		try {
			if(employeeFlowId != null)
				this.employeeFlowService.deleteEmployeeFlow(employeeFlowId);
			else
				throw new Exception("Employee Flow ID is null.");

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@QueryMapping
	public List<EmployeeSubsectionStatusInfo> allEmployeeSubsectionStatuses(){
		return this.employeeSubsectionStatusService.retrieveAllEmployeeSubsectionStatus();
	}

	@QueryMapping
	public List<EmployeeSubsectionStatusInfo> allEmployeeSubsectionStatusesById(@Argument Long id){
		return this.employeeSubsectionStatusService.retrieveEmployeeSubsectionStatusByEmployeeId(id);
	}

	@MutationMapping
	public EmployeeSubsectionStatusInfo upsertEmployeeSubsectionStatus(@Argument EmployeeSubsectionStatusForm form){
		try{
			if(form != null)
				return this.employeeSubsectionStatusService.upsertEmployeeSubsectionStatus(form);
			else
				throw new Exception("Employee Subsection Status is null.");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}

	}

	@MutationMapping
	public Boolean deleteEmployeeSubsectionStatus(@Argument Long employeeSubsectionStatusId){
		try {
			if(employeeSubsectionStatusId != null)
				this.employeeSubsectionStatusService.deleteEmployeeSubsectionStatus(employeeSubsectionStatusId);
			else
				throw new Exception("Employee Flow ID is null.");

			return true;
		} catch (Exception ex) {
			return false;
		}
	}


}
