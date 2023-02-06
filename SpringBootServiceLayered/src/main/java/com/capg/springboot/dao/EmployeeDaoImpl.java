package com.capg.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.springboot.entity.Employee;
import com.capg.springboot.exception.EmployeeAlreadyExistsException;
import com.capg.springboot.exception.EmployeeNotFoundException;
import com.capg.springboot.repository.EmployeeRepository;
@Service
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	private EmployeeRepository repository;
	@Override
	public List<Employee> getEmployees() {
		return repository.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException {
		Employee emp;
		if(repository.findById(employeeId).isEmpty()) {
			throw new EmployeeNotFoundException();
		}
		else {
			emp=repository.findById(employeeId).get();
		}
		return emp;
	}

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
		if(repository.existsById(employee.getEmployeeId())) {
			throw new EmployeeAlreadyExistsException();
		}
	return repository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public void deleteEmployee(long employeeId) {
Employee emp=repository.getOne(employeeId);
repository.delete(emp);
		
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return this.repository.findByEmployeeName(name);
	}

	@Override
	public List<Employee> getEmployeeByDesignation(String desgination) {
		return this.repository.displayByDesignation(desgination);
	}

}
