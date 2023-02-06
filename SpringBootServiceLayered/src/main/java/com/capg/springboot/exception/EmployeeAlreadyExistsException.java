package com.capg.springboot.exception;

public class EmployeeAlreadyExistsException extends Exception {
private String message;
public EmployeeAlreadyExistsException() {}
public EmployeeAlreadyExistsException(String message) {
	this.message=message;
}
public String getMessage() {
	return this.message;
}
}
