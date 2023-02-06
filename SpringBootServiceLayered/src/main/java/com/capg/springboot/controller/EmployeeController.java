package com.capg.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.springboot.dao.EmployeeDao;
import com.capg.springboot.entity.Employee;
import com.capg.springboot.exception.EmployeeAlreadyExistsException;
import com.capg.springboot.exception.EmployeeNotFoundException;

import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping
	public List<Employee>  getEmployees(){
		
	return this.employeeDao.getEmployees();
	}
	
	
	@GetMapping("/{employeeId}")
	public ResponseEntity getEmployeeById(@PathVariable long employeeId) throws EmployeeNotFoundException {
		return new ResponseEntity(this.employeeDao.getEmployeeById(employeeId),HttpStatus.OK);
		
	//	return this.employeeDao.getEmployeeById(employeeId);
	}
	
	@PostMapping
	public ResponseEntity addEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
		Employee emp= this.employeeDao.addEmployee(employee);
		return new ResponseEntity(emp,HttpStatus.CREATED);
	}
	
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		return this.employeeDao.updateEmployee(employee);
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long employeeId) {
		try {
		this.employeeDao.deleteEmployee(employeeId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/name/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name){
		return this.employeeDao.getEmployeeByName(name);
	}
	
	@GetMapping("/designation/{desgination}")
	public List<Employee> getEmployeeByDesignation(@PathVariable String desgination){
		return this.employeeDao.getEmployeeByDesignation(desgination);
	}
	
}
