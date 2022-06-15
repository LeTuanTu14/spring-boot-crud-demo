package com.se.springbootcruddemo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.springbootcruddemo.dao.EmployeeDAO;
import com.se.springbootcruddemo.entity.Employee;

@RestController
@RequestMapping("api")
public class EmployeeRestController {
	
	private EmployeeDAO employeeDAO;
		
	@Autowired
	public EmployeeRestController(@Qualifier("employeeDaoJpaImpl") EmployeeDAO theEmployeeDAO) {
		employeeDAO= theEmployeeDAO;
	}

	@PostMapping("/employees")
	public Employee saveEmployee(@RequestBody Employee theEmployee) {

		theEmployee.setId(0);
		employeeDAO.saveEmployee(theEmployee);
		return theEmployee;

	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeDAO.saveEmployee(theEmployee);
		return theEmployee;
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Employee theEmployee = employeeDAO.getEmployee(id);
		if (theEmployee == null) {
			throw new EmployeeNotFoundException("Employee is not found - " + id);
		}
		employeeDAO.deleteEmployee(id);
		return "Delete Employee id= " + id;
	}

	@GetMapping("/employees")
	public List<Employee> listEmployees() {
		return employeeDAO.getEmployees();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		Employee theEmployee = employeeDAO.getEmployee(id);
		if (theEmployee == null) {
			throw new EmployeeNotFoundException("Employee is not found - " + id);
		}
		return theEmployee;
	}


}
