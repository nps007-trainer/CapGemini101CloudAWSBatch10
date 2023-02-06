package com.capg.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.springboot.entity.Employee;

@RestController
public class RestTemplateController {
	
	static final String URL_EMPLOYEES = "http://localhost:8080/employee";
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/restemployees")
    public ResponseEntity getAllEmployees() {
        ResponseEntity < Employee[] > responseEntity = restTemplate.getForEntity(URL_EMPLOYEES, Employee[].class);
        return responseEntity;
    }
}
