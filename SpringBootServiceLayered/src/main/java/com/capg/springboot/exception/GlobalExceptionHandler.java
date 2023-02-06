package com.capg.springboot.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@Value(value="${message1}")
	private String message1;
	
	@Value(value="${message2}")
	private String message2;
	
	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity  employeeNotFoundException(EmployeeNotFoundException ex) {
		return new ResponseEntity(message1,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=EmployeeAlreadyExistsException.class)
	public ResponseEntity  employeeyAlreadyExistsException(EmployeeAlreadyExistsException ex) {
		return new ResponseEntity(message2,HttpStatus.CONFLICT);
	}
}
