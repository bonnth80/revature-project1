package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
 * Servlet implementation class ApplyServlet
 */
@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Apply Servlet: doGet() called");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();		
		Gson gson = new Gson();
		User user = (User)request.getSession().getAttribute("user");
		AccountBoImp abo = new AccountBoImp();
		try {
			int newId = abo.getMaxAccountNumber()+1;
			Float amount = Float.parseFloat(request.getParameter("amount"));
			Account account = new Account(
					newId,
					user.getUserId(),
					new Date(),
					0,
					amount,
					amount);
			
			abo.addNewAccount(account);
			System.out.println("Apply doPost: new account processed");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
