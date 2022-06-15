package com.se.springbootcruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.springbootcruddemo.entity.Employee;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoJpaImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		Query theQuery = entityManager.createQuery("from Employee");
		List<Employee> employees= theQuery.getResultList();
		return employees;
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		Employee dbEmployee = entityManager.merge(theEmployee);
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {
		Employee theEmployee= entityManager.find(Employee.class, theId);
		return theEmployee;
	}

	@Override
	@Transactional
	public void deleteEmployee(int theId) {
		Query theQuery =  entityManager.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
