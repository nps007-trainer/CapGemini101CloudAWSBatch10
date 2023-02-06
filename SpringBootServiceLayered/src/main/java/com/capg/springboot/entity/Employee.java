package com.capg.springboot.entity;



import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Builder
public class Employee implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
private long employeeId;
	@Column(name="employee_name",nullable = false)
private String employeeName;
	@Column(name="designation", nullable=false)
private String designation;

}
