package com.lmph.be.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.lmph.be.dto.EmployeeInfo;
import com.lmph.be.entity.Address;
import com.lmph.be.entity.Contact;
import com.lmph.be.enums.Gender;
import com.lmph.be.enums.MaritalStatus;
import com.lmph.be.form.AddressInput;
import com.lmph.be.form.ContactInput;
import com.lmph.be.form.EmployeeForm;

/**
 * Employee Service Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
@Transactional
class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
			
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * @Act 
	 * 	  get single employee
	 * @Asserts
     *     Should return null
	 */
	@Test
	@Sql("classpath:sql/truncate.sql")
	public void getEmployee_returnsNull() {
		
		EmployeeInfo actual = this.employeeService.getEmployee(1L);		
		assertThat(actual).isNull();
		
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * 	 use 1L as employeeId
	 * @Act 
	 * 	  get single employee
	 * 	  call getEmployee
	 * @Asserts
     *     Should return instance of EmployeeInfo
	 */
	@Test
	@Sql( scripts = {"classpath:sql/truncate.sql", "classpath:sql/insert_record.sql"})
	public void getEmployee_returnsEmployeeInfo() {
		
		EmployeeInfo actual = this.employeeService.getEmployee(1L);		
		assertThat(actual).isInstanceOf(EmployeeInfo.class);
	}
	
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * @Act 
	 * 	  get list of employees
	 * 	  call getEmployees()
	 * @Asserts
     *     Should return empty list
	 */
	@Test	
	@Sql("classpath:sql/truncate.sql")
	public void getEmployees_returnsEmptyList() {
		
		List<EmployeeInfo> actual = this.employeeService.getEmployees();
		
		assertThat(actual).isEmpty();
	}
	
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * @Act 
	 * 	  get list of employees
	 * 	  call getEmployees()
	 * @Asserts
     *     Should return list and not empty
	 */
	@Test	
	@Sql( scripts = {"classpath:sql/truncate.sql", "classpath:sql/insert_record.sql"})
	public void getEmployees_returnsList() {
		
		List<EmployeeInfo> actual = this.employeeService.getEmployees();
		
		assertThat(actual).isNotEmpty();
		assertThat(actual.size()).isNotEqualTo(0);
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * @Act 
	 * 	  add new employee
	 * 	  do not add addresses and contacts
	 * 	  call upsert()
	 * @Asserts
     *     Should return employeeInfo
     *     assert not addresses and contacts
     *     assert employee data are the same as in the input
	 */
	@Test
	@Sql("classpath:sql/truncate.sql")
	public void upsert_addEmployeeSuccess() {
		
		EmployeeForm employeeForm = new EmployeeForm();
		
		employeeForm.setId(null);
		employeeForm.setFirstName("Juan");
		employeeForm.setLastName("Dela Cruz");
		employeeForm.setMiddleName("M");
		employeeForm.setBirthdate( LocalDate.parse("1990-01-01") );
		employeeForm.setCompanyPosition("Employee");
		employeeForm.setDateHired( LocalDate.parse("2010-01-01") );
		employeeForm.setMaritalStatus( MaritalStatus.S.toString() );
		employeeForm.setGender( Gender.M.toString() );
		
		employeeForm.setAddresses( new ArrayList<>() );
		employeeForm.setContacts( new ArrayList<>() );
		
		EmployeeInfo actual = this.employeeService.upsert(employeeForm);
		EmployeeInfo expected = this.employeeService.getEmployee( actual.getId() );
		
		assertThat(actual).isInstanceOf(EmployeeInfo.class);
		assertThat(actual.getId()).isNotNull();		
		assertThat(actual.getAddresses()).isEmpty();
		assertThat(actual.getContacts()).isEmpty();				
		assertThat(actual).isEqualTo(expected);
		
		// assert all field inputs are saved
		assertThat(
				List.of( actual.getFirstName(), 
						actual.getLastName(),
						actual.getMiddleName(),
						actual.getBirthdate(),
						actual.getGender(),
						actual.getMaritalStatus(),
						actual.getCompanyPosition(),
						actual.getDateHired()
					)
				
				)
				.containsAll(
						List.of(employeeForm.getFirstName(), 
								employeeForm.getLastName(),
								employeeForm.getMiddleName(),
								employeeForm.getBirthdate(),
								employeeForm.getGender(),
								employeeForm.getMaritalStatus(),
								employeeForm.getCompanyPosition(),
								employeeForm.getDateHired()
						)
				);
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * @Act 
	 * 	  add new employee
	 * 	  add addresses and contacts
	 * 	  call upsert()
	 * @Asserts
     *     Should return employeeInfo
     *     assert addresses and contacts are saved
     *     assert addresses and contacts are the same as in the input
	 */
	@Test
	@Sql("classpath:sql/truncate.sql")
	public void upsert_addAddressAndContactSuccess() {
		
		EmployeeForm employeeForm = new EmployeeForm();
		
		employeeForm.setId(null);
		employeeForm.setFirstName("Juan");
		employeeForm.setLastName("Dela Cruz");
		employeeForm.setMiddleName("M");
		employeeForm.setBirthdate( LocalDate.parse("1990-01-01") );
		employeeForm.setCompanyPosition("Employee");
		employeeForm.setDateHired( LocalDate.parse("2010-01-01") );
		employeeForm.setMaritalStatus( MaritalStatus.S.toString() );
		employeeForm.setGender( Gender.M.toString() );
		
		AddressInput address1 = new AddressInput();
		address1.setAddress1("1st Street");
		address1.setAddress2("test");
		address1.setIsPrimary(true);
		
		AddressInput address2 = new AddressInput();
		address2.setAddress1("2st Street");
		address2.setAddress2("test 2");
		address2.setIsPrimary(false);
		
		ContactInput contact1 = new ContactInput();
		contact1.setContactNo("111-1111111");
		contact1.setIsPrimary(true);
		
		ContactInput contact2 = new ContactInput();
		contact2.setContactNo("222-2222222");
		contact2.setIsPrimary(false);
		
		employeeForm.setAddresses( List.of(address1, address2) );
		employeeForm.setContacts( List.of(contact1, contact2) );
		
		EmployeeInfo actual = this.employeeService.upsert(employeeForm);
							
		assertThat(actual.getAddresses().size()).isEqualTo( employeeForm.getAddresses().size() );
		assertThat(actual.getContacts().size()).isEqualTo( employeeForm.getContacts().size() );			
				
		// assert address and contact inputs are saved
		Address actualAddress = actual.getAddresses().get(0);
		
		assertThat(actualAddress.getAddress1()).isEqualTo(address1.getAddress1());
		assertThat(actualAddress.getAddress2()).isEqualTo(address1.getAddress2());
		assertThat(actualAddress.getIsPrimary()).isEqualTo(address1.getIsPrimary());
		
		actualAddress = actual.getAddresses().get(1);
		
		assertThat(actualAddress.getAddress1()).isEqualTo(address2.getAddress1());
		assertThat(actualAddress.getAddress2()).isEqualTo(address2.getAddress2());
		assertThat(actualAddress.getIsPrimary()).isEqualTo(address2.getIsPrimary());
		
		Contact actualContact = actual.getContacts().get(0);
		assertThat(actualContact.getContactNo()).isEqualTo(contact1.getContactNo());
		assertThat(actualContact.getIsPrimary()).isEqualTo(contact1.getIsPrimary());
		
		actualContact = actual.getContacts().get(1);
		assertThat(actualContact.getContactNo()).isEqualTo(contact2.getContactNo());
		assertThat(actualContact.getIsPrimary()).isEqualTo(contact2.getIsPrimary());
		
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * 	 insert record
	 * @Act 
	 * 	  get single employee
	 * 	  update employee data
	 * 	  call upsert
	 * @Asserts
     *     assert that the same record has been updated. assert id is the same
     *     assert that record data are the same in the inputs
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncate.sql", "classpath:sql/insert_record.sql" })
	public void upsert_updateEmployeeSuccess() {
		
		// fetch record first
		List<EmployeeInfo> list = this.employeeService.getEmployees();
		EmployeeInfo employeeInfo = list.get(0);
		
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setId( employeeInfo.getId() );
		employeeForm.setFirstName("Juan Edited");
		employeeForm.setLastName("Dela Cruz Edited");
		employeeForm.setMiddleName("M Edited");
		employeeForm.setBirthdate( LocalDate.parse("1990-01-01") );
		employeeForm.setCompanyPosition("Employee Edited");
		employeeForm.setDateHired( LocalDate.parse("2010-01-01") );
		employeeForm.setMaritalStatus( MaritalStatus.U.toString() );
		employeeForm.setGender( Gender.U.toString() );
		
		EmployeeInfo actual = this.employeeService.upsert(employeeForm);
		
		assertThat(actual.getId()).isEqualTo( employeeInfo.getId() );
		assertThat(actual).isNotEqualTo(employeeInfo);
		
		assertThat(actual.getFirstName()).isEqualTo(employeeForm.getFirstName());
		assertThat(actual.getLastName()).isEqualTo(employeeForm.getLastName());
		assertThat(actual.getMiddleName()).isEqualTo(employeeForm.getMiddleName());
		assertThat(actual.getBirthdate()).isEqualTo(employeeForm.getBirthdate());
		assertThat(actual.getCompanyPosition()).isEqualTo(employeeForm.getCompanyPosition());
		assertThat(actual.getDateHired()).isEqualTo(employeeForm.getDateHired());
		assertThat(actual.getMaritalStatus()).isEqualTo(employeeForm.getMaritalStatus());
		assertThat(actual.getGender()).isEqualTo(employeeForm.getGender());
		
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * 	 insert record
	 * @Act 
	 * 	  get single employee
	 * 	  update address data
	 * 	  call upsert
	 * @Asserts
     *     assert that the same address record has been updated. assert id is the same
     *     assert that address record data are the same in the inputs
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncate.sql", "classpath:sql/insert_record.sql" })
	public void upsert_updateAddressSuccess() {
		
		// fetch record first
		List<EmployeeInfo> list = this.employeeService.getEmployees();
		EmployeeInfo employeeInfo = list.get(0);
		
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setId( employeeInfo.getId() );
		employeeForm.setFirstName("Juan Edited");
		employeeForm.setLastName("Dela Cruz Edited");
		employeeForm.setMiddleName("M Edited");
		employeeForm.setBirthdate( LocalDate.parse("1990-01-01") );
		employeeForm.setCompanyPosition("Employee Edited");
		employeeForm.setDateHired( LocalDate.parse("2010-01-01") );
		employeeForm.setMaritalStatus( MaritalStatus.U.toString() );
		employeeForm.setGender( Gender.U.toString() );
		
		Address address = employeeInfo.getAddresses().get(0);
		
		AddressInput addressInput = new AddressInput();
		addressInput.setId( address.getId() );
		addressInput.setAddress1("edited address");
		addressInput.setAddress2("edited address 2");
		
		employeeForm.setAddresses( List.of(addressInput) );
		
		EmployeeInfo employeeInfoActual = this.employeeService.upsert(employeeForm);
		
		Address actual = employeeInfoActual.getAddresses().get(0);
		
		assertThat(employeeInfoActual.getAddresses().size()).isEqualTo(employeeInfo.getAddresses().size());
		assertThat(actual.getId()).isEqualTo(address.getId());
		assertThat(actual.getAddress1()).isEqualTo(addressInput.getAddress1());
		assertThat(actual.getAddress2()).isEqualTo(addressInput.getAddress2());
		
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * 	 insert record
	 * @Act 
	 * 	  get single employee
	 * 	  update contact data
	 * 	  call upsert
	 * @Asserts
     *     assert that the same contact record has been updated. assert id is the same
     *     assert that contact record data are the same in the inputs
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncate.sql", "classpath:sql/insert_record.sql" })
	public void upsert_updateContactSuccess() {
		
		// fetch record first
		List<EmployeeInfo> list = this.employeeService.getEmployees();
		EmployeeInfo employeeInfo = list.get(0);
		
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setId( employeeInfo.getId() );
		employeeForm.setFirstName("Juan Edited");
		employeeForm.setLastName("Dela Cruz Edited");
		employeeForm.setMiddleName("M Edited");
		employeeForm.setBirthdate( LocalDate.parse("1990-01-01") );
		employeeForm.setCompanyPosition("Employee Edited");
		employeeForm.setDateHired( LocalDate.parse("2010-01-01") );
		employeeForm.setMaritalStatus( MaritalStatus.U.toString() );
		employeeForm.setGender( Gender.U.toString() );
		
		Contact contact = employeeInfo.getContacts().get(0);
		
		ContactInput contactInput = new ContactInput();
		contactInput.setId( contact.getId() );
		contactInput.setContactNo("0000000000000");
		
		employeeForm.setContacts( List.of(contactInput) );
		
		EmployeeInfo employeeInfoActual = this.employeeService.upsert(employeeForm);
		
		Contact actual = employeeInfoActual.getContacts().get(0);
		
		assertThat(employeeInfoActual.getContacts().size()).isEqualTo( employeeInfo.getContacts().size() );
		assertThat(actual.getId()).isEqualTo(contact.getId());
		assertThat(actual.getContactNo()).isEqualTo(contactInput.getContactNo());				
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * 	 insert record
	 * @Act 
	 * 	  get single employee
	 * 	  empty address and contact list inputs
	 * 	  call upsert
	 * @Asserts
     *     assert contacts and addresses are empty list
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncate.sql", "classpath:sql/insert_record.sql" })
	public void upsert_removeAddressesAndContactsSuccess() {
		
		List<EmployeeInfo> list = this.employeeService.getEmployees();
		EmployeeInfo employeeInfo = list.get(0);
		
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setId( employeeInfo.getId() );
		employeeForm.setFirstName("Juan Edited");
		employeeForm.setLastName("Dela Cruz Edited");
		employeeForm.setMiddleName("M Edited");
		employeeForm.setBirthdate( LocalDate.parse("1990-01-01") );
		employeeForm.setCompanyPosition("Employee Edited");
		employeeForm.setDateHired( LocalDate.parse("2010-01-01") );
		employeeForm.setMaritalStatus( MaritalStatus.U.toString() );
		employeeForm.setGender( Gender.U.toString() );
		
		employeeForm.setAddresses( null );
		employeeForm.setContacts( null );
		
		EmployeeInfo employeeInfoActual = this.employeeService.upsert(employeeForm);
		
		assertThat(employeeInfoActual.getAddresses()).isEmpty();
		assertThat(employeeInfoActual.getContacts()).isEmpty();
	}
	
	
	/**
	 * @Arrangements 
	 * 	 truncate table
	 * 	 insert record
	 * @Act 
	 * 	  get single employee
	 * 	  call delete
	 * @Asserts
     *     assert the deleted record does not exists anymore
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncate.sql", "classpath:sql/insert_record.sql" })
	public void delete_success() {
		
		List<EmployeeInfo> list = this.employeeService.getEmployees();
		EmployeeInfo employeeInfo = list.get(0);
		
		this.employeeService.delete( employeeInfo.getId() );
		
		EmployeeInfo actual = this.employeeService.getEmployee(employeeInfo.getId());
		
		assertThat(actual).isNull();
	}

}
