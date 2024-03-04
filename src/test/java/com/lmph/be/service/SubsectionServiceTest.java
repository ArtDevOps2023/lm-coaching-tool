package com.lmph.be.service;

import static org.assertj.core.api.Assertions.assertThat;
 
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.lmph.be.dto.SubsectionInfo;
import com.lmph.be.form.SubsectionForm;

/**
 * Subsection Service Unit Test
 * @author Ryan Valmoria
 */
@SpringBootTest
@Transactional
class SubsectionServiceTest {

	@Autowired
	private SubsectionService subsectionService;
	

	/**
	 * @Arrangements 
	 * 	truncate the subsection table
	 * 	use 1L as subsectionId
	 * @Act 
	 *	get a single subsection
	 * @Asserts
     *	should return null
	 */
	@Test
	@Sql("classpath:sql/truncateSubsection.sql")
	public void getSubsectionBySubsectionId_returnsNull() {
		
		SubsectionInfo actual = this.subsectionService.getSubsectionBySubsectionId(1L);		
		assertThat(actual).isNull();
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate the section table
	 *   insert a record in the section table with 1L as sectionId
	 * 	 insert a record in the subsection table using 1L as sectionId foreign key
	 * 	 use 1L as subsectionId
	 * @Act 
	 * 	  get single subsection
	 * 	  call getSubsectionBySubsectionId
	 * @Asserts
     *	should return instance of SubsectionInfo
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql", "classpath:sql/insertRecordSubsection.sql" })
	public void getSubsectionBySubsectionId_returnsSubsectionInfo() {
		
		SubsectionInfo actual = this.subsectionService.getSubsectionBySubsectionId(1L);		
		assertThat(actual).isInstanceOf(SubsectionInfo.class);
	}
	
	
	/**
	 * @Arrangements 
	 * 	truncate the subsection table
	 * 	use 1L as sectionId
	 * @Act 
	 * 	  get list of all subsections by sectionId
	 * 	  call getAllSubsectionsBySectionId()
	 * @Asserts
     *     Should return null
	 */
	@Test
	@Sql("classpath:sql/truncateSubsection.sql")
	public void getAllSubsectionsBySectionId_returnsNull() {
		
		List<SubsectionInfo> actual = this.subsectionService.getAllSubsectionsBySectionId(1L);		
		assertThat(actual).isNull();
	}
	
	/**
	 * @Arrangements 
	 *   truncate the section table first
	 *   insert a record in the section table with 1L as sectionId
	 * 	 insert a record in the subsection table using 1L as sectionId foreign key
	 * @Act 
	 * 	  get list of all subsections by sectionId
	 * 	  call getAllSubsectionsBySectionId()
	 * @Asserts
     *	should return a list of subsection and not empty
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql", "classpath:sql/insertRecordSubsection.sql" })
	public void getAllSubsectionsBySectionId_returnsListOfSubsection() {
		
		List<SubsectionInfo> actual = this.subsectionService.getAllSubsectionsBySectionId(1L);
		
		assertThat(actual).isNotEmpty();
		assertThat(actual.size()).isNotEqualTo(0);
	}
	
	
	/**
	 * @Arrangements 
	 * 	 truncate the subsection table
	 * @Act 
	 * 	  get list of all subsection
	 * 	  call getAllSubsections()
	 * @Asserts
     *	should return empty list
	 */
	@Test
	@Sql("classpath:sql/truncateSubsection.sql")
	public void getAllSubsections_returnsEmptyList() {
		
		List<SubsectionInfo> actual = this.subsectionService.getAllSubsections();
		assertThat(actual).isEmpty();
	}
	
	
	/**
	 * @Arrangements 
	 *   truncate the section table first
	 *   insert a record in the section table with 1L as sectionId
	 * 	 insert a record in the subsection table using 1L as sectionId foreign key
	 * @Act 
	 * 	  get list of all subsections
	 * 	  call getAllSubsections()
	 * @Asserts
     *	should return a list of subsection and not empty
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql", "classpath:sql/insertRecordSubsection.sql" })
	public void getAllSubsections_returnsListOfSubsection() {
		
		List<SubsectionInfo> actual = this.subsectionService.getAllSubsections();
		
		assertThat(actual).isNotEmpty();
		assertThat(actual.size()).isNotEqualTo(0);
	}
	
	
	
	/**
	 * @Arrangements 
	 *   truncate the section table first
	 *   insert a record in the section table with 1L as sectionId
	 * @Act 
	 * 	  add a new subsection
	 * 	  call upsert()
	 * @Asserts
     *	should return SubsectionInfo
     * 	assert subsection data are the same as in the input
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql"})
	public void upsert_addSubsectionSuccess() {
		
		SubsectionForm subsectionForm = new SubsectionForm();
		
		subsectionForm.setSubsectionId(null);
		subsectionForm.setSectionId(1L);
		subsectionForm.setDescription("Local Special Holidays");
		subsectionForm.setFacilitator("HR Staff");
		subsectionForm.setDetails("LMPH Holidays");
		subsectionForm.setTargetDay("Day 10");
		subsectionForm.setTargetSprint("Sprint 5");
		 
		SubsectionInfo actual = this.subsectionService.upsert(subsectionForm);
		SubsectionInfo expected = this.subsectionService.getSubsectionBySubsectionId( actual.getSubsectionId() );
		
		assertThat(actual).isInstanceOf(SubsectionInfo.class);
		assertThat(actual.getSubsectionId()).isNotNull();			
		assertThat(actual).isEqualTo(expected);
		
		// assert all field inputs are saved
		assertThat(
					List.of(actual.getSection().getSectionId(),actual.getDescription(),actual.getFacilitator(),actual.getDetails(),
							actual.getTargetDay(),actual.getTargetSprint())
				).containsAll(
					List.of(subsectionForm.getSectionId(),subsectionForm.getDescription(),subsectionForm.getFacilitator(),subsectionForm.getDetails(),
							subsectionForm.getTargetDay(),subsectionForm.getTargetSprint())
				);
	}
	
	
	
	
	
	/**
	 * @Arrangements 
	 * 	 truncate the section table first
	 *   insert a record in the section table with 1L as sectionId
	 * 	 insert a record in the subsection table using 1L as sectionId foreign key
	 * @Act 
	 * 	get a single subsection
	 * 	update subsection data
	 * 	call upsert
	 * @Asserts
     *	assert that the same record has been updated. assert id is the same
     * 	assert that record data are the same in the inputs
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql", "classpath:sql/insertRecordSubsection.sql" })
	public void upsert_updateSubsectionSuccess() {
		
		// fetch record first
		List<SubsectionInfo> list = this.subsectionService.getAllSubsections();
		SubsectionInfo subsectionInfo = list.get(0);
		
		
		SubsectionForm subsectionForm = new SubsectionForm();
		subsectionForm.setSubsectionId( subsectionInfo.getSubsectionId() );
		subsectionForm.setSectionId(subsectionInfo.getSection().getSectionId());
		subsectionForm.setDescription("Local Special Holidays");
		subsectionForm.setFacilitator("HR Staff");
		subsectionForm.setDetails("LMPH Holidays");
		subsectionForm.setTargetDay("Day 10");
		subsectionForm.setTargetSprint("Sprint 5");
		
		SubsectionInfo actual = this.subsectionService.upsert(subsectionForm);
		
		assertThat(actual.getSubsectionId()).isEqualTo( subsectionInfo.getSubsectionId() );
		assertThat(actual).isNotEqualTo(subsectionInfo);
		assertThat(actual.getSection().getSectionId()).isEqualTo( subsectionInfo.getSection().getSectionId());
		assertThat(actual.getDescription()).isEqualTo(subsectionForm.getDescription());
		assertThat(actual.getFacilitator()).isEqualTo(subsectionForm.getFacilitator());
		assertThat(actual.getDetails()).isEqualTo(subsectionForm.getDetails());
		assertThat(actual.getTargetDay()).isEqualTo(subsectionForm.getTargetDay());
		assertThat(actual.getTargetSprint()).isEqualTo(subsectionForm.getTargetSprint());
		
	}
	
	
	/**
	 * @Arrangements 
	 * 	 truncate the section table first
	 *   insert a record in the section table with 1L as sectionId
	 * 	 insert a record in the subsection table using 1L as sectionId foreign key
	 * @Act 
	 * 	get the first subsection
	 *	call deleteSubsection
	 * @Asserts
     *	assert the deleted record does not exists anymore
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql", "classpath:sql/insertRecordSubsection.sql" })
	public void deleteSubsection_success() {
		
		List<SubsectionInfo> subsectionList = this.subsectionService.getAllSubsections();
		SubsectionInfo subsectionInfo = subsectionList.get(0);
		
		this.subsectionService.deleteSubsection(subsectionInfo.getSubsectionId());
		
		SubsectionInfo actual = this.subsectionService.getSubsectionBySubsectionId(subsectionInfo.getSubsectionId());
		
		assertThat(actual).isNull();
	}

}
