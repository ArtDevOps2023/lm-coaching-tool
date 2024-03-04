package com.lmph.be.controller;

import java.util.List;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.lmph.be.dto.SubsectionInfo;
import com.lmph.be.form.SubsectionForm;
import com.lmph.be.service.SubsectionService;



/**
 * Subsection Controller class
 * @author Ryan Valmoria
 */
@Controller
public class SubsectionController {

	@Autowired
	private SubsectionService subsectionService;
	 
	
	/**
	 * GraphQL controller for fetching all subsections
	 * @return List<SubsectionInfo>
	 */
	@QueryMapping
	public List<SubsectionInfo> subsection() {
		return this.subsectionService.getAllSubsections();
	}
	
	/**
	 * GraphQL controller for adding and updating a subsection
	 * @param form
	 * @return SubsectionInfo
	 */
	@MutationMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public SubsectionInfo upsertSubsection(@Valid @Argument SubsectionForm form) throws ConstraintViolationException {
		return this.subsectionService.upsert(form);	
	}
	
	/**
	 * GraphQL controller for deleting a subsection
	 * @param subsectionId
	 * @return Boolean
	 */
	@MutationMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Boolean deleteSubsection(@Argument Long subsectionId) {
		this.subsectionService.deleteSubsection(subsectionId);
		return true;
	}
}
