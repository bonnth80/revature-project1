package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.novabank.exception.BusinessException;
import com.novabank.to.User;
import com.novabank.userBO.UserBoImp;

/**
 * Servlet implementation class PortalServlet
 */
@WebServlet("/employeesignin")
public class PortalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("application/json");
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		try {
			User user = new UserBoImp().getUserByCredentials(uname, pword);
			if (user.getArchetype() == 0) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				out.print("{\"code\": 1, \"message\": \"Redirecting...\"}");
			} else if (user.getArchetype() == 1) {
				out.print("{\"code\": 0, \"message\": \"Wrong user type. Customers should log in at http://127.0.0.1:1235/TheBank/index.html\"}");
			}
		} catch (BusinessException e) {
			System.out.println("Error: " + e);
		}
	}

}
