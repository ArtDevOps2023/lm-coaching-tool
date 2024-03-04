package com.lmph.be.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * EmployeeInfo DTO Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
class EmployeeInfoTest {

	private EmployeeInfo employeeInfo;
	
	@BeforeEach
	public void init() {
		
		employeeInfo = new EmployeeInfo();
	}
	
	@Test
	public void getAge_nullValueReturnsZero() {
		
		employeeInfo.setBirthdate(null);
		assertThat(employeeInfo.getAge()).isEqualTo(0);
	}
	
	@Test
	public void getAge_withValueReturnsCorrectYears() {
		
		LocalDate date = LocalDate.parse("1978-01-01");		
		Period period = Period.between(LocalDate.now(), date);
		Integer expected = Math.abs(period.getYears());
		
		employeeInfo.setBirthdate( date );
		assertThat(employeeInfo.getAge()).isEqualTo(expected);
	}
	
	@Test
	public void getYearsOfExperience_nullValueReturnsZero() {
		
		employeeInfo.setDateHired(null);
		assertThat(employeeInfo.getYearsOfExperience()).isEqualTo(0);
	}
	
	@Test
	public void getYearsOfExperience_returnsCorrectYears() {
		
		LocalDate date = LocalDate.parse("2020-01-01");		
		Period period = Period.between(LocalDate.now(), date);
		Integer expected = Math.abs(period.getYears());
		
		employeeInfo.setDateHired(date);
		assertThat(employeeInfo.getYearsOfExperience()).isEqualTo(expected);
	}
}
