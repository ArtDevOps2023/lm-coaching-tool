package com.lmph.be.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

import com.lmph.be.enums.SectionColor;


/**
 * Section Form to be used in section_form.html frontend template
 * @author Ryan Valmoria
 */
@Data
public class SectionForm implements Serializable {
 
	private static final long serialVersionUID = 1L;

	/**
	 * Mapped to sectionId hidden field
	 */
	private Long sectionId;

	/**
	 * Mapped to Section Name field
	 */
	@NotEmpty( message = "Section Name is required." )
	@Size( max = 100, message = "Section Name must be less than or equal to 100 characters")
	private String name;
	
	/**
	 * Mapped to Section Color field
	 */
	@NotBlank( message = "Section Color is required.")
	private String color;
	
	/**
	 * Color options for Section Color dropdown field
	 */
	private SectionColor sectionColors = SectionColor.B;
	
}
