package com.lmph.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lmph.be.entity.Employee;

/**
 * Employee Dao implementation class
 * @author Jhun Tiballa
 */
public interface EmployeeDao extends JpaRepository<Employee, Long>{}
