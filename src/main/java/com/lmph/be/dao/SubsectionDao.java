package com.lmph.be.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmph.be.entity.Section;
import com.lmph.be.entity.Subsection;

/**
 * Subsection Dao implementation class
 * @author Ryan Valmoria
 */
public interface SubsectionDao extends JpaRepository<Subsection, Long> {
	/**
	 * Query to get Subsection list by sectionId which is a foreign key of Subsection
	 * @param section
	 * @return Optional<Subsection>
	 */
	Optional<Subsection> findBySection(Section section);
}
