package com.capg.springboot.dao;

import java.util.List;

import com.capg.springboot.entity.Employee;
import com.capg.springboot.exception.EmployeeAlreadyExistsException;
import com.capg.springboot.exception.EmployeeNotFoundException;

public interface EmployeeDao {
public List<Employee>  getEmployees();
public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException;
public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException;
public Employee updateEmployee(Employee employee);
public void deleteEmployee(long employeeId);
public List<Employee> getEmployeeByName(String name);
public List<Employee> getEmployeeByDesignation(String desgination);

}
