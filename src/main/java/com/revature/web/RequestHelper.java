package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {

	// A logger
	private static Logger logger = Logger.getLogger(RequestHelper.class);
	// an employee service instance
	private static EmployeeService eserv = new EmployeeService(new EmployeeDAO());
	// an object mapper
	private static ObjectMapper om = new ObjectMapper();

	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// extract params from request to get (username, password)

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		logger.info("User attempted to login with username " + username);
		
		
		// cal confimLogin() with those values
		Employee e = eserv.confirmLogin(username, password);
		
		// if user is not null save to session and print to client
		
		if (e != null) {
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			// print the logged in user to the screen
			PrintWriter out = response.getWriter();
		
			
			// convert obj with jackson mapper and print it out
			out.println(om.writeValueAsString(e));
			
		}else {
			// if returned object is null return a HTTP status called no content
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
			response.setStatus(204);
			
		}
		// call service layer which calls dao

		// return response or redirect user if emploee object exists in the DB

	}

	public static void processEmployees(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// set content type of response
		response.setContentType("text/html");

		// call the findAll() method from service and save to a list
		List<Employee> allEmployees = eserv.findAll(); // calls our DAO layer which retrieves all objcets from DB
		// marshal the list java objects to json (using jackson as our Object mapper)
		String jsonString = om.writeValueAsString(allEmployees);
		// call print writer to write to client in response bocy
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	public static void processError(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("error.html").forward(request, response);
		/*
		 * foward() differse from sendRedirect() in that it does not produce a new request,
		 * just forwards the same request to a new resource (maintaining the URL)
		 */
	}

}
