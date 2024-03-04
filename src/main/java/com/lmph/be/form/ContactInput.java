package com.lmph.be.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Contact Input
 * @author Jhun Tiballa
 */
@Data
public class ContactInput implements Serializable {
	
	private static final long serialVersionUID = 3419365481063768532L;

	private Long id;
	
	@NotEmpty( message = "Contact No must not be empty." )
	@Size( max = 30, message = "Contact No must be less than or equal to 30 characters")
	private String contactNo;
	
	private Boolean isPrimary;
}
