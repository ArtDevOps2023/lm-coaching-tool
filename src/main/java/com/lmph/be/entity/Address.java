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
 * Address Entity
 * @author Jhun Tiballa
 */
@Data
@Entity
@Table(name = "addresses")
public class Address {

	/**
	 * primary key
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
			
	/**
	 * Address 1
	 */
	private String address1;
	
	/**
	 * Address 2
	 */
	private String address2;
	
	/**
	 * Is primary address
	 */
	private Boolean isPrimary;
	
	/**
	 * Employee
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
	private Employee employee;
}
