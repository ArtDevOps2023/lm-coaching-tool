package com.lmph.be.entity;

import java.time.LocalDate;
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
 * Entity User class
 * @author Jhun Tiballa
 */
@Data
@Entity
@Table(name = "employees")
public class Employee {

	/**
	 * Primary key
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToMany(mappedBy="employee", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contact> contacts = new ArrayList<>();
	
	/**
	 * Employee's addresses
	 */
	@OneToMany(mappedBy="employee", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Address> addresses = new ArrayList<>();


	@OneToMany(mappedBy="employee", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EmployeeFlow> employeeFlows = new ArrayList<>();
	
	/**
	 * Date created
	 */
	private LocalDateTime createdAt;
	
	/**
	 * Date updated
	 */
	private LocalDateTime updatedAt;

	public Employee() {
	}

	public Employee(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", middleName='" + middleName + '\'' +
				'}';
	}
}
