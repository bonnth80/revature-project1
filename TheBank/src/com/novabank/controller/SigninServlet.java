package com.novabank.controller;

import java.io.IOException;

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
 * Servlet implementation class SigninServlet
 */
@WebServlet({ "/SigninServlet", "/customersignin" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");

		try {
			User user = new UserBoImp().getUserByCredentials(uname, pword);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//response.setContentType("text/html;charset=UTF-8");
//			System.out.println("sending redirect now, hypothetically");
//			response.sendRedirect("user.html");
		} catch (BusinessException e) {
			System.out.println("Error: " + e);
		}
		
	}

}
