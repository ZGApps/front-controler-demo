package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAO {

	public List<Employee> findAll(){
		
		// grab session
		Session ses = HibernateUtil.getSession();
		
		// HQL statement to return all records from Employee table
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		// there is also the Critera API and also Native SQL are other ways to write complex querys
		
		// return list
		return emps;
	}
	
	public int insert(Employee e) {
		// grab session
		Session ses = HibernateUtil.getSession();

		// begin transaction
		Transaction tx = ses.beginTransaction();

		// capture PK returned from save
		int pk = (int) ses.save(e);

		// commit transaction
		tx.commit();

		// return PK
		return pk;
	}

	public boolean update(Employee e) {

		return false;
	}

	public boolean delete(Employee e) {

		return false;
	}


}
