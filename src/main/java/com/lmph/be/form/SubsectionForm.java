package com.lmph.be.form;

import java.io.Serializable; 
 
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * Subsection Form to be used in subsection_form.html frontend template
 * @author Ryan Valmoria
 */
@Data
public class SubsectionForm implements Serializable {
 
	private static final long serialVersionUID = 1L;

	/**
	 * Mapped to subsectionId hidden field
	 */
	private Long subsectionId;
	
	/**
	 * Mapped to Description field
	 */
	@NotEmpty( message = "Subsection Name is required." )
	@Size( max = 100, message = "Subsection Name must be less than or equal to 100 characters.")
	private String description;
	
	/**
	 * Mapped to Facilitator field
	 */
	private String facilitator;
	
	/**
	 * Mapped to Details field
	 */
	private String details;
	
	/**
	 * Mapped to Target Day field
	 */
	private String targetDay;
	
	/**
	 * Mapped to Target Sprint field
	 */
	private String targetSprint;
	
	/**
	 * Mapped to sectionId hidden field
	 */
	private Long sectionId;
	
}
