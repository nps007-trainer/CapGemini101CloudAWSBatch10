package com.capg.springboot.exception;

public class EmployeeNotFoundException extends Exception {
private String message;
public EmployeeNotFoundException() {}
public EmployeeNotFoundException(String message) {
	this.message=message;
}
public String getMessage() {
	return this.message;
}
}
