package com.se.springbootcruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.springbootcruddemo.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Employee> theQuery= currentSession.createQuery("from Employee", Employee.class);
		
		List<Employee> employees= theQuery.getResultList();
		
		return employees;
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee the= currentSession.get(Employee.class, theId);
		return the;
	}

	@Override
	@Transactional
	public void deleteEmployee(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> theQuery= currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
