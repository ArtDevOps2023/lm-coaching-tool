package com.lmph.be.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Address Input
 * @author Jhun Tiballa
 */
@Data
public class AddressInput implements Serializable {

	private static final long serialVersionUID = 1327323109550772008L;

	private Long id;
	
	@NotEmpty( message = "Address field must not be empty." )
	@Size( max = 100, message = "Address must be less than or equal to 100 characters")
	private String address1;
	
	private String address2;
	
	private Boolean isPrimary;
}
