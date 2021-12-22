package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {

	private EmployeeDAO mockdao;
	private EmployeeService eserv;
	
	@Before // before every test
	
public void setup() {
		
		mockdao = mock(EmployeeDAO.class);
		
		eserv = new EmployeeService(mockdao);
	}
	
	@After
	public void teardown() {
		mockdao = null;
		eserv = null;
	}
	
	
	//Happy Path Scenario => test we get expected result when things go right
	@Test
	public void testConfirmLogin_success() {
		Employee e1 = new Employee(3, "Scott", "Lank", "Antman", "bugs");
		Employee e2 = new Employee(23, "Clint", "Barton", "Hawkeye", "arrows");
		
		// we create a fake dB of employee objects
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);

		// when mockdao findAll() is called it returns dummy list (List of stubs)
		when(mockdao.findAll()).thenReturn(emps);
		
		
		//use assert equals on the eserv.confirmLogin() method that we return the right user
		
		assertEquals(e2, eserv.confirmLogin("Hawkeye", "arrows"));
		// when we call confirm login with username Hawkeye and password arrows, we return Employee et

	}
	
	@Test
	public void testConfirmLogin_Fail() {
		Employee e1 = new Employee(3, "Scott", "Lank", "Antman", "bugs");
		Employee e2 = new Employee(23, "Clint", "Barton", "Hawkeye", "arrows");
		
		// we create a fake dB of employee objects
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);

		// when mockdao findAll() is called it returns dummy list (List of stubs)
		when(mockdao.findAll()).thenReturn(emps);
		
		
		//use assert equals on the eserv.confirmLogin() method that we return the right user
		
		assertNull(eserv.confirmLogin("Hawkeye", "arrow"));

	}
	
}
