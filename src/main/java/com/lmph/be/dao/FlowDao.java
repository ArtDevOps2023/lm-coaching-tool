package com.lmph.be.dao;

import com.lmph.be.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access object interface for Flow
 *
 * @author Jeffrey John Javison
 * @since 06-Dec-2023
 */
public interface FlowDao extends JpaRepository<Flow, Long> {
}
