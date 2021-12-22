package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;

/*
 * Service level is responsible for business (or application) logic
 * while DAO is responsible for persistence logic. 
 * 
 */
public class EmployeeService {

	// service class needs to call on the DAO layer to perform ops on the Employee objects
	private EmployeeDAO edao;
	
	/*
	 * Dependency injection by way of constructor injection, every time an object of this class is created it has a fully 
	 * initialized DAO because it has to have one passed in.
	 */
	
	// constructor injection
	public EmployeeService(EmployeeDAO edao) {
		super();
		this.edao = edao;
	}
	/*
	 * confirm login method will return employee that has successfully logged in, meaning that their username and password 
	 * match a unique record in the db, if username and password do not match return null.
	 */
	//Confirm login
	public Employee confirmLogin(String username, String password) {
		
		// call findAll()
		Optional<Employee> possibleEmp = edao.findAll()
				// stream through records and find the first that matches the given username and password
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
		
		return (possibleEmp.isPresent() ? possibleEmp.get() : null);
		

	}
	
	// find all
	public List<Employee> findAll(){
		return edao.findAll();
	}
	public int insert(Employee e) {
		return edao.insert(e);
	}
	
	public boolean update(Employee e) {
		return edao.update(e);
	}
	
	public boolean delete(Employee e) {
		return edao.delete(e);
	}
	
}
