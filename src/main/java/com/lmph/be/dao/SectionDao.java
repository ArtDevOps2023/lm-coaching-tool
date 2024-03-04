package com.lmph.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lmph.be.entity.Section;

/**
 * Section Dao implementation class
 * @author Ryan Valmoria
 */
public interface SectionDao extends JpaRepository<Section, Long>{}
