package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.novabank.accountBO.AccountBoImp;
import com.novabank.exception.BusinessException;
import com.novabank.to.Account;
import com.novabank.to.User;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		System.out.println(request.getSession().getAttribute("user"));
		User user = (User)request.getSession().getAttribute("user");
		try {
			List<Account> accounts = new AccountBoImp().getAccountsByUserId(user.getUserId());
			String json = gson.toJson(accounts);
			System.out.println(json);
			out.print(gson.toJson(accounts));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
