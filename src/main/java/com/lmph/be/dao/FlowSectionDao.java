package com.lmph.be.dao;

import com.lmph.be.entity.FlowSection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access object interface for Flow's Sections
 *
 * @author Jeffrey John Javison
 * @since 06-Dec-2023
 */
public interface FlowSectionDao extends JpaRepository<FlowSection, Long> {

    void deleteByflowId(Long id);
}
