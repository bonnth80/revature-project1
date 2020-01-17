package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.novabank.exception.BusinessException;
import com.novabank.to.User;
import com.novabank.ui.Menu;
import com.novabank.userBO.UserBoImp;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet({ "/SigninServlet", "/customersignin" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SigninServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		log.info("SigninServlet (doPost)");
		response.setContentType("application/json");
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");
		PrintWriter out = response.getWriter();

		log.info("SigninServlet (doPost): uname: " + uname + " pword: ****");
		try {
			User user = new UserBoImp().getUserByCredentials(uname, pword);
			if (user.getArchetype() == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				log.info("SigninServlet (doPost): {\\\"code\\\": 1, \\\"message\\\": \\\"Redirecting...\\\"}");
				out.print("{\"code\": 1, \"message\": \"Redirecting...\"}");
			} else if (user.getArchetype() == 0) {

				log.info("SignInServlet (doPost): {\\\"code\\\": 0, \\\"message\\\": \\\"Wrong user type. Employees should log in at http://127.0.0.1:1235/TheBank/portal.html\\\"}");
				out.print("{\"code\": 0, \"message\": \"Wrong user type. Employees should log in at http://127.0.0.1:1235/TheBank/portal.html\"}");
			}
		} catch (BusinessException e) {
			log.warn("SigninServlet (doPost): err=" + e);
		}
	}

}
