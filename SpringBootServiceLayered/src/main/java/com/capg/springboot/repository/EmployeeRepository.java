package com.capg.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capg.springboot.entity.Employee;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {
	
	// convention
	public List<Employee> findByEmployeeName(String name);
	
	// user customized method--- no jpa convention
		@Query("Select e from Employee e where e.designation=:designation")
		public List<Employee> displayByDesignation(@Param(value = "designation") String designation);
	
	
	}
