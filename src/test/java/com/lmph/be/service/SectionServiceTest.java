package com.lmph.be.service;

import static org.assertj.core.api.Assertions.assertThat;
 
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.lmph.be.dto.SectionInfo;
import com.lmph.be.dto.SubsectionInfo;
import com.lmph.be.form.SectionForm;
 

/**
 * Section Service Unit Test
 * @author Ryan Valmoria
 */
@SpringBootTest
@Transactional
class SectionServiceTest {

	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private SubsectionService subsectionService;
	
	/**
	 * @Arrangements 
	 * 	truncate the section table
	 * 	use 1L as sectionId
	 * @Act 
	 *  get a single section
	 * @Asserts
     *  should return null
	 */
	@Test
	@Sql("classpath:sql/truncateSection.sql")
	public void getSectionBySectionId_returnsNull() {
		
		SectionInfo actual = this.sectionService.getSectionBySectionId(1L);		
		assertThat(actual).isNull();
	}
	
	/**
	 * @Arrangements 
	 *	truncate the section table first
	 *	insert a record in the section table
	 *	use 1L as sectionId
	 * @Act 
	 *	get single section
	 *	call getSectionBySectionId
	 * @Asserts
     *  should return instance of SectionInfo
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql" })
	public void getSectionBySectionId_returnsSectionInfo() {
		
		SectionInfo actual = this.sectionService.getSectionBySectionId(1L);		
		assertThat(actual).isInstanceOf(SectionInfo.class);
	}
	
	
	/**
	 * @Arrangements 
	 *	truncate the section table
	 * @Act 
	 *	get list of all sections
	 *	call getAllSections()
	 * @Asserts
     *	should return empty list
	 */
	@Test
	@Sql("classpath:sql/truncateSection.sql")
	public void getAllSections_returnsEmptyList() {
		
		List<SectionInfo> actual = this.sectionService.getAllSections();
		
		assertThat(actual).isEmpty();
	}
	
	
	/**
	 * @Arrangements 
	 *	truncate the section table first
	 *	insert a record in the section table
	 * @Act 
	 *	get list of all sections
	 *	call getAllSections()
	 * @Asserts
     * should return a list of section and not empty
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql" })
	public void getAllSections_returnsListOfSection() {
		
		List<SectionInfo> actual = this.sectionService.getAllSections();
		
		assertThat(actual).isNotEmpty();
		assertThat(actual.size()).isNotEqualTo(0);
	}
	
	
	/**
	 * @Arrangements 
	 * 	 truncate the section table
	 * @Act 
	 * 	  add a new section
	 * 	  call upsert()
	 * @Asserts
     *     Should return SectionInfo
     *     assert section data are the same as in the input
	 */
	@Test
	@Sql("classpath:sql/truncateSection.sql")
	public void upsert_addSectionSuccess() {
		
		SectionForm sectionForm = new SectionForm();
		
		sectionForm.setSectionId(null);
		sectionForm.setName("HR Processes");
		sectionForm.setColor("G");
		 
		SectionInfo actual = this.sectionService.upsert(sectionForm);
		SectionInfo expected = this.sectionService.getSectionBySectionId( actual.getSectionId() );
		
		assertThat(actual).isInstanceOf(SectionInfo.class);
		assertThat(actual.getSectionId()).isNotNull();			
		assertThat(actual).isEqualTo(expected);
		
		// assert all field inputs are saved
		assertThat(
					List.of(actual.getName(),actual.getColor())
				).containsAll(
					List.of(sectionForm.getName(),sectionForm.getColor())
				);
	}
	
	/**
	 * @Arrangements 
	 * 	 truncate the section table
	 * 	 insert a record in the section table
	 * @Act 
	 * 	  get a single section
	 * 	  update section data
	 * 	  call upsert
	 * @Asserts
     *     assert that the same record has been updated. assert id is the same
     *     assert that record data are the same in the inputs
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql" })
	public void upsert_updateSectionSuccess() {
		
		// fetch record first
		List<SectionInfo> list = this.sectionService.getAllSections();
		SectionInfo sectionInfo = list.get(0);
		
		SectionForm sectionForm = new SectionForm();
		sectionForm.setSectionId( sectionInfo.getSectionId() );
		sectionForm.setName("Backend Exam");
		sectionForm.setColor("B");
		
		SectionInfo actual = this.sectionService.upsert(sectionForm);
		
		assertThat(actual.getSectionId()).isEqualTo( sectionInfo.getSectionId() );
		assertThat(actual).isNotEqualTo(sectionInfo);
		
		assertThat(actual.getName()).isEqualTo(sectionForm.getName());
		assertThat(actual.getColor()).isEqualTo(sectionForm.getColor());
		
	}

	/**
	 * @Arrangements 
	 * 	 truncate the section table
	 *   insert a record in the section table with 1L as sectionId
	 * 	 insert a record in the subsection table using 1L as sectionId foreign key
	 * @Act 
	 * 	  get the first section on the list
	 * 	  call deleteSection
	 * @Asserts
     *     assert that the deleted record does not exists anymore section table
     *     assert that all the subsections related to that section are all deleted also
	 */
	@Test
	@Sql( scripts = { "classpath:sql/truncateSection.sql", "classpath:sql/insertRecordSection.sql" , "classpath:sql/insertRecordSubsection.sql"})
	public void deleteSection_success() {
		
		List<SectionInfo> sectionList = this.sectionService.getAllSections();
		SectionInfo sectionInfo = sectionList.get(0);
		
		this.sectionService.deleteSection(sectionInfo.getSectionId());
		
		SectionInfo actual = this.sectionService.getSectionBySectionId(sectionInfo.getSectionId());
		List<SubsectionInfo> subsectionList = this.subsectionService.getAllSubsectionsBySectionId(sectionInfo.getSectionId());
		
		assertThat(actual).isNull();
		assertThat(subsectionList).isNull();
	}
	
}
