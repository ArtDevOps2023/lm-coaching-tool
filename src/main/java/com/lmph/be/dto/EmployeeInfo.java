package com.lmph.be.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.lmph.be.entity.Address;
import com.lmph.be.entity.Contact;

import lombok.Data;

/**
 * Employee Info DTO
 * @author Jhun Tiballa
 */
@Data
public class EmployeeInfo  {
	
	/**
	 * Primary key
	 */
	private Long id;
		
	/**
	 * First Name
	 */
	private String firstName;
	
	/**
	 * Last Name
	 */
	private String lastName;
	
	/**
	 * Middle name
	 */
	private String middleName;
		
	/**
	 * Birthdate
	 */
	private LocalDate birthdate;
	
	/**
	 * Gender
	 */
	private String gender;
	
	/**
	 * Martital Status
	 */
	private String maritalStatus;
	
	/**
	 * Position
	 */
	private String companyPosition;
	
	/**
	 * Date Hired
	 */
	private LocalDate dateHired;
	
	/**
	 * Employee's contacts
	 */
	private List<Contact> contacts = new ArrayList<>();
	
	/**
	 * Employee's addresses
	 */
	private List<Address> addresses = new ArrayList<>();
	
	/**
	 * Date created
	 */
	private LocalDateTime createdAt;
	
	/**
	 * Date updated
	 */
	private LocalDateTime updatedAt;

	private List<EmployeeFlowInfo> employeeFlowInfos = new ArrayList<>();
	
	public Integer getAge() {
		
		if(  this.getBirthdate() != null ) {
			Period period = Period.between(LocalDate.now(), this.getBirthdate());
			return Math.abs(period.getYears());
		}
		
		return 0;
	}
	
	public Integer getYearsOfExperience() {
		if(  this.getDateHired() != null ) {
			Period period = Period.between(LocalDate.now(), this.getDateHired());
			return Math.abs(period.getYears());
		}
		
		return 0;
	}

	public String getFullName() {
		return firstName + " " + middleName + " " + lastName;
	}

}
