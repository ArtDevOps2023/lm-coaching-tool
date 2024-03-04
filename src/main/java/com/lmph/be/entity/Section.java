package com.lmph.be.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * Section Entity
 * @author Ryan Valmoria
 */
@Data
@Entity
@Table(name = "section")
public class Section {

	/**
	 * primary key : section_id
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sectionId;

	/**
	 * name
	 */
	private String name;
	
	/**
	 * color
	 * 
	 * blue - means the section has to be customized / reviewed per team
	 * green - means the section is generic, common for all roles and can be used by all teams
	 * yellow - means the section is managed or co-managed by the practice lead
	 */
	private String color;
	
	/**
	 * created_by
	 */
	private String createdBy;
	
	/**
	 * created_date
	 */
	private LocalDateTime createdDate;
	
	/**
	 * Subsections per Section
	 */
	@OneToMany(mappedBy="section", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Subsection> subsectionList = new ArrayList<>();


	public Section(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Section(){}
}
