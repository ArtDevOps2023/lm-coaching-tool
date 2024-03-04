package com.lmph.be.service;
 

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmph.be.dao.SectionDao;
import com.lmph.be.dto.SectionInfo;
import com.lmph.be.entity.Section;
import com.lmph.be.enums.SectionColor;
import com.lmph.be.form.SectionForm;


/**
 * Section Service class
 * @author Ryan Valmoria
 */
@Service
public class SectionService {

	@Autowired
	private SectionDao sectionDao;
	 
	/**
	 * Fetch a particular section by sectionId
	 * @param sectionId
	 * @return SectionInfo
	 */
	public SectionInfo getSectionBySectionId(Long sectionId) {		
			 		
		Optional<Section> optSection = this.sectionDao.findById(sectionId);
					
		if (optSection.isPresent()) {
			SectionInfo sectionInfo = new SectionInfo();
			Section section = optSection.get();
			BeanUtils.copyProperties(section, sectionInfo);
			return sectionInfo;	
		} else {
			return null;
		}				
	}
		
	/**
	 * Fetch all sections
	 * @return List<SectionInfo>
	 */
	public List<SectionInfo> getAllSections() {		
		List<Section> sectionList = this.sectionDao.findAll();
					
		return sectionList.stream().map( section -> {
			SectionInfo sectionInfo = new SectionInfo();
			BeanUtils.copyProperties(section, sectionInfo);
			sectionInfo.setColorDescription(SectionColor.getLabelOfColor(sectionInfo.getColor()));
			return sectionInfo;
		})
		.collect(Collectors.toList());
	}
	
	/**
	 * Method for adding and updating a section
	 * @param form
	 * @return SectionInfo
	 */
	public SectionInfo upsert(SectionForm form) {
		
		SectionInfo sectionInfo = new SectionInfo();
		Section section = new Section();
		
		BeanUtils.copyProperties(form, section);
		section = this.sectionDao.saveAndFlush(section);
		
		BeanUtils.copyProperties(section, sectionInfo);
		
		return sectionInfo;
	}
		
	/**
	 * Method to delete a particular section
	 * @param sectionId
	 */
	public void deleteSection(Long sectionId)  {
		this.sectionDao.deleteById(sectionId);
	}
}
