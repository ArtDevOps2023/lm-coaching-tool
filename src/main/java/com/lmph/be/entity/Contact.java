package com.lmph.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Contact Entity
 * @author Jhun Tiballa
 */
@Data
@Entity
@Table(name = "contacts")
public class Contact {
	
	/**
	 * Primary key
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Contact Number
	 */
	private String contactNo;
	
	/**
	 * Is primary contact
	 */
	private Boolean isPrimary;
	
	/**
	 * Employee
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
	private Employee employee;	
}
