package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.novabank.accountBO.AccountBoImp;
import com.novabank.exception.BusinessException;
import com.novabank.to.Account;

/**
 * Servlet implementation class AccountByNumberServlet
 */
@WebServlet("/accountbynumber")
public class AccountByNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		int accountid = Integer.parseInt(request.getParameter("accountid"));
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		try {
			Account account = new AccountBoImp().getAccountByAccountNumber(accountid);
			String res = gson.toJson(account);
			out.print(res);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
