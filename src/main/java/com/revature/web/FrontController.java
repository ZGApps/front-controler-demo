package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * This method will be responsible for determining what resource the client is requesting
	 * IT will clean the URL and only capture the end part
	 * 
	 * Once it captures the destination it calls tha RequestHelper which will supply the right functionality
	 * 
	 * if it receives "http://localhost:8080/FrontControllerDemo/login" it will capture just "login"
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//first save URI and rewrite it
		final String URI = request.getRequestURI().replace("/FrontControllerDemo/", "");
		
		// second we make a switch statement to call the correct functinoality
		
		switch(URI) {
		case "login":
			// call login method
			RequestHelper.processLogin(request, response);
			break;
		case "employees":
			//This method will return all users to the client
			RequestHelper.processEmployees(request, response);
			break;
		case "error":
			// call some method that processes a 404 error
			RequestHelper.processError(request, response);
			break;
		default:
			// call some method that processes a 404 error
			RequestHelper.processError(request, response);
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// this makes it so when a post method comes in it runs it through the doget() method instead
		doGet(request, response);
	}

}
