package com.nucsoft.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nucsoft.employee.model.Employee;
import com.nucsoft.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository empRepo;

	public EmployeeService(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		for (Employee e : empRepo.findAll()) {
			if (!e.isDeleted()) {
				list.add(e);
			}
		}
		return list;
	}

	public Employee createEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	public Employee updateEmployee(long id, Employee employee) {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp.isPresent()) {
			Employee newEmployee = emp.get();
			newEmployee.setAge(employee.getAge());
			newEmployee.setDateOfJoining(employee.getDateOfJoining());
			newEmployee.setEmailId(employee.getEmailId());
			newEmployee.setName(employee.getName());
			return empRepo.save(newEmployee);
		}
		return null;
	}

	public Employee deleteEmployee(long id) {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp.isPresent()) {
			Employee employee = emp.get();
			employee.setDeleted(true);
			empRepo.save(employee);
			return employee;
		}
		return null;
	}

}
