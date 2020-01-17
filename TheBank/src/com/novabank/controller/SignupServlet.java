package com.novabank.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.novabank.exception.BusinessException;
import com.novabank.to.User;
import com.novabank.userBO.UserBoImp;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SignupServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("SignupServlet (doPost)");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String archetype = request.getParameter("archetype");
		String ssn = request.getParameter("ssn");
		String homePhone = request.getParameter("homePhone");
		String mobilePhone = request.getParameter("mobilePhone");
		String email = request.getParameter("email");
		String streetAddress = request.getParameter("streetAddress");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String zip = request.getParameter("zip");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		UserBoImp ubi = new UserBoImp();
		try {
			int newId = ubi.getMaxIdUsed() + 1;			
			
			User user = new User(
					newId,
					firstName,
					lastName,
					Integer.parseInt(archetype),
					ssn,
					homePhone,
					mobilePhone,
					email,
					streetAddress,
					city,
					state,
					country,
					zip,
					userName,
					password,
					new Date()
					);
			log.warn("SignupServlet (doPost): add new user=" + user);
			ubi.addNewUser(user);
		} catch (BusinessException e) {
			log.warn("SignupServlet (doPost): err=" + e);
		}
		
	
		
		
		
	}


}
