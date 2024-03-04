package com.lmph.be.dto;
 
 
import java.util.ArrayList;
import java.util.List;

import com.lmph.be.entity.Subsection;

import lombok.Data;

/**
 * Section Info DTO
 * @author Ryan Valmoria
 */
@Data
public class SectionInfo  {
	
	/**
	 * ID of the Section
	 */
	private Long sectionId;
		
	/**
	 * Name of the Section
	 */
	private String name;
	
	/**
	 * Color for the Section
	 */
	private String color;
	
	/**
	 * Description of the selected color
	 */
	private String colorDescription;

	/**
	 * List of Subsections per Section
	 */
	private List<Subsection> subsectionList = new ArrayList<>();
	 
}
