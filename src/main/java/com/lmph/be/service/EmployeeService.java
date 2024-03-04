package com.lmph.be.service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lmph.be.utility.DTOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmph.be.dao.EmployeeDao;
import com.lmph.be.dto.EmployeeInfo;
import com.lmph.be.entity.Address;
import com.lmph.be.entity.Contact;
import com.lmph.be.entity.Employee;
import com.lmph.be.form.AddressInput;
import com.lmph.be.form.ContactInput;
import com.lmph.be.form.EmployeeForm;


/**
 * User Service class
 * 
 * @author Jhun Tiballa
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * Fetch employee
	 * @param id
	 * @return EmployeeInfo
	 */
	public EmployeeInfo getEmployee(Long id) {		
			 		
		Optional<Employee> optEmployee = this.employeeDao.findById(id);
					
		if(optEmployee.isPresent()) {
			EmployeeInfo employeeInfo = new EmployeeInfo();
			Employee employee = this.employeeDao.findById(id).get();
			BeanUtils.copyProperties(employee, employeeInfo);

			employeeInfo.setEmployeeFlowInfos(
					employee.getEmployeeFlows().stream().map(DTOUtil::toEmployeeFlowInfo).toList());

			return employeeInfo;
		}
		else {
			return null;
		}				
	}
		
	/**
	 * Fetch all employees
	 * @return
	 */
	public List<EmployeeInfo> getEmployees() {		
		List<Employee> employeeList = this.employeeDao.findAll();
					
		return employeeList.stream().map( employee -> {
			EmployeeInfo employeeInfo = new EmployeeInfo();
			BeanUtils.copyProperties(employee, employeeInfo);
			return employeeInfo;
		})
		.collect(Collectors.toList());
		
	}
	
	/**
	 * Upsert
	 * @param form
	 * @return
	 */
	public EmployeeInfo upsert(EmployeeForm form) {
		
		EmployeeInfo employeeInfo = new EmployeeInfo();
		Employee employee = new Employee();
		
		BeanUtils.copyProperties(form, employee);
		
		employee.getAddresses().clear();
		employee.getContacts().clear();
		
		if( Objects.nonNull(form.getAddresses()) ) {
			for( AddressInput addressInput : form.getAddresses()) {
				Address address = new Address();
				BeanUtils.copyProperties(addressInput, address);
				address.setEmployee(employee);
				employee.getAddresses().add(address);
			}
		}
		
		if( Objects.nonNull(form.getContacts()) ) {
			for( ContactInput contactInput : form.getContacts()) {
				Contact contact = new Contact();
				BeanUtils.copyProperties(contactInput, contact);
				contact.setEmployee(employee);
				employee.getContacts().add(contact);
			}
		}
		
		employee = this.employeeDao.saveAndFlush(employee);
		
		BeanUtils.copyProperties(employee, employeeInfo);
		
		return employeeInfo;
	}
		
	/**
	 * Delete
	 * @param id
	 */
	public void delete(Long id)  {
		this.employeeDao.deleteById(id);
	}
}
