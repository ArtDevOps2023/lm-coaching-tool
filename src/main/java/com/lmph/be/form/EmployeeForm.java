package com.lmph.be.form;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.PastOrPresent;

import com.lmph.be.enums.Gender;
import com.lmph.be.enums.MaritalStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Employee Form class
 * @author Jhun Tiballa
 */
@Data
public class EmployeeForm implements Serializable{

	private static final long serialVersionUID = -3287695944471226774L;

	private Long id;
		
	@NotEmpty( message = "Firstname is required." )
	@Size( max = 100, message = "First name must be less than or equal to 100 characters")
	private String firstName;
	
	@NotEmpty( message = "Last name is required." )
	@Size( max = 100, message = "Last name must be less than or equal to 100 characters.")
	private String lastName;
	
	@NotEmpty( message = "Middle name is required." )
	@Size( max = 100, message = "Middle name must be less than or equal to 100 characters")
	private String middleName;
		
	@NotNull
	@PastOrPresent( message = "Invalid Birtdate value.")
	private LocalDate birthdate;
	
	@NotBlank( message = "Gender is required.")
	private String gender;
	
	@NotBlank( message = "Marital status is required.")
	private String maritalStatus;
	
	@NotEmpty( message = "Position is required." )
	private String companyPosition;
	
	@NotNull
	@PastOrPresent( message = "Invalid date value.")
	private LocalDate dateHired;
		
	private List<ContactInput> contacts;
		
	private List<AddressInput> addresses;	
	
	private Gender genders = Gender.M;
	
	private MaritalStatus maritalStatuses = MaritalStatus.S;
	
}
