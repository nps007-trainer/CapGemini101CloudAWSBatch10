package com.capg.springboot;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.capg.springboot.dao.EmployeeDao;
import com.capg.springboot.entity.Employee;
import com.capg.springboot.exception.EmployeeAlreadyExistsException;
import com.capg.springboot.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest
public class EmployeeControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeDao employeeDao;
	
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void employee_whencreate_thenReturnSaved() throws JsonProcessingException, Exception {
		// given - precondition or setup
		Employee employee=Employee.builder() 
				.employeeName("Nandini")
                .designation("Trainer")
                .build();

		given(employeeDao.addEmployee(any(Employee.class)))
		.willAnswer(invocation -> invocation.getArgument(0));
		
		
		// when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/employee")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(employee)));
		
        // then - verify the result or output using assert statements
        response.andDo(print()).
        andExpect(status().isCreated())
        .andExpect(jsonPath("$.employeeName",
                is(employee.getEmployeeName())))
        .andExpect(jsonPath("$.designation",
                is(employee.getDesignation())));
       
	}
	// JUnit test for Get All employees REST API
    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception{
        // given - precondition or setup
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().employeeName("Xyz").designation("developer").build());
        listOfEmployees.add(Employee.builder().employeeName("PQR").designation("designer").build());
        given(employeeDao.getEmployees()).willReturn(listOfEmployees);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/employee"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfEmployees.size())));

    }

    // positive scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
        // given - precondition or setup
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .employeeName("Nandini")
                .designation("Trainer")
                
                .build();
        given(employeeDao.getEmployeeById(employeeId)).willReturn(employee);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/employee/{employeeId}", employeeId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.employeeName", is(employee.getEmployeeName())))
                .andExpect(jsonPath("$.designation", is(employee.getDesignation())));
                

    }

    // negative scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
    	// given - precondition or setup
        long employeeId = 1L;
        Employee employee = Employee.builder()
                .employeeName("Nandini")
                .designation("Trainer")
                
                .build();
        given(employeeDao.getEmployeeById(employeeId)).willReturn(employee);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/employee/{employeeId}", employeeId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.employeeName", is(employee.getEmployeeName())))
                .andExpect(jsonPath("$.designation", is(employee.getDesignation())));
                

    }
    // JUnit test for update employee REST API - positive scenario
        @Test
        public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception{
            // given - precondition or setup
            long employeeId = 1L;
            Employee savedEmployee = Employee.builder()
                    .employeeName("Nandini")
                    .designation("Trainer")
                    .build();

            Employee updatedEmployee = Employee.builder()
            		.employeeName("Nandini")
                    .designation("Corporate Trainer")
                    .build();
           
            given(employeeDao.updateEmployee(any(Employee.class)))
                    .willAnswer((invocation)-> invocation.getArgument(0));

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(put("/employee")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(updatedEmployee)));


            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.employeeName", is(updatedEmployee.getEmployeeName())))
                    .andExpect(jsonPath("$.designation", is(updatedEmployee.getDesignation())));
                    
        }

   

// JUnit test for delete employee REST API
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception{
        // given - precondition or setup
        long employeeId = 1L;
        willDoNothing().given(employeeDao).deleteEmployee(employeeId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/employee/{employeeId}", employeeId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
