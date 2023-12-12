package com.nucsoft.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nucsoft.employee.model.Employee;
import com.nucsoft.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	private final EmployeeService employeeService;
	
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.createEmployee(employee));
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(id, employee));
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployees(@PathVariable long id) {
		Employee emp = employeeService.deleteEmployee(id);
		if (emp != null) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

}
