package com.lmph.be.dto;
 

import com.lmph.be.entity.Section;

import lombok.Data;

/**
 * Subsection Info DTO
 * @author Ryan Valmoria
 */
@Data
public class SubsectionInfo  {
	
	/**
	 * ID of the Subsection
	 */
	private Long subsectionId;
	
	/**
	 * Description of the Subsection
	 */
	private String description;
	
	/**
	 * Facilitator of the Subsection
	 */
	private String facilitator;
	
	/**
	 * Details and References of the Subsection
	 */
	private String details;
	
	/**
	 * Target Day to finish the Subsection task
	 */
	private String targetDay;
	
	/**
	 *  Target Sprint to finish the Subsection task
	 */
	private String targetSprint;
	
	/**
	 * Parent Section of this Subsection. Mapped by section_id as foreign key.
	 */
	private Section section;
}
