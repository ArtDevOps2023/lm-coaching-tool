package com.lmph.be.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

/**
 * Subsection Entity
 * @author Ryan Valmoria
 */
@Data
@Entity
@Table(name = "subsection")
public class Subsection {

	/**
	 * primary key : subsection_id
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subsectionId;
	
	/**
	 * description
	 */
	private String description;
	
	/**
	 * facilitator
	 */
	private String facilitator;
	
	/**
	 * details
	 */
	private String details;
	
	/**
	 * target_day
	 */
	private String targetDay;
	
	/**
	 * target_sprint
	 */
	private String targetSprint;
	
	/**
	 * sort_order
	 */
	private int sortOrder;
	
	/**
	 * Section
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="section_id")
	private Section section;

	public Subsection() {
	}

	public Subsection(Long subsectionId) {
		this.subsectionId = subsectionId;
	}
}
