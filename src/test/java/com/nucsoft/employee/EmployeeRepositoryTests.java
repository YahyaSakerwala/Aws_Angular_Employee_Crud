package com.nucsoft.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nucsoft.employee.model.Employee;
import com.nucsoft.employee.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Order(1)
	//@Rollback(value = false)
	void saveEmplTest() {
		Employee emp = new Employee();
		emp.setName("testUser");
		emp.setEmailId("testuser@hhh.com");
		emp.setAge(29);
		emp.setDateOfJoining(LocalDate.of(2023, 11, 03));
		Employee savedEmp = employeeRepository.save(emp);
		assertThat(savedEmp.getId()).isPositive();
	}

	@Test
	@Order(2)
	void getEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		assertThat(employee.getId()).isEqualTo(1);
	}

	@Test
	@Order(3)
	void getListOfEmployeesTest() {
		List<Employee> employees = employeeRepository.findAll();
		assertThat(employees).isNotEmpty();
	}
	
	@Test
    @Order(4)
    //@Rollback(value = false)
    void updateEmployeeTest(){
        Employee employee = employeeRepository.findById(1L).get();
        employee.setAge(22);
        employee.setName("ram");
        employee.setEmailId("ram@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);
        assertThat(employeeUpdated.getEmailId()).isEqualTo("ram@gmail.com");
    }

    @Test
    @Order(5)
    void deleteEmployeeTest(){
        Employee employee = employeeRepository.findById(1L).get();
        employee.setDeleted(true);
        employeeRepository.save(employee);
        Employee employee1 = null;
        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId("ram@gmail.com");
        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
            assertThat(employee1.isDeleted()).isTrue();
        }
       
    }

}
