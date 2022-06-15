package com.se.springbootcruddemo.dao;

import java.util.List;

import com.se.springbootcruddemo.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getEmployees();
    public void saveEmployee(Employee theEmployee);
    public Employee getEmployee(int theId);
    public void deleteEmployee(int theId);

}
