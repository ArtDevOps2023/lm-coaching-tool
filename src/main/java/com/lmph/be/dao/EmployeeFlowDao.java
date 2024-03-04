package com.lmph.be.dao;

import com.lmph.be.entity.EmployeeFlow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Data access object interface for Employee's Flow
 *
 * @author Jeffrey John Javison
 * @since 06-Dec-2023
 */
public interface EmployeeFlowDao extends JpaRepository<EmployeeFlow, Long> {

    List<EmployeeFlow> findByemployeeId(Long employeeId);

    void deleteByemployeeId(Long employeeId);

}
