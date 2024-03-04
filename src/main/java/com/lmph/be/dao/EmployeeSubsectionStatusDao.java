package com.lmph.be.dao;

import com.lmph.be.entity.EmployeeSubsectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSubsectionStatusDao extends JpaRepository<EmployeeSubsectionStatus, Long> {

    /**
     * @author Jeffrey John Javison
     * @since Phase 2.1 12-Dec-2023
     * @param employeeId
     * @return
     */
    List<EmployeeSubsectionStatus> findByemployeeId(Long employeeId);

}
