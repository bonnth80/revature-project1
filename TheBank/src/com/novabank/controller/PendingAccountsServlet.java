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

/**
 * Servlet implementation class PendingAccountsServlet
 */
@WebServlet("/pendingaccounts")
public class PendingAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // Get pending accounts
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		try {
			List<Account> accounts = new AccountBoImp().getAccountsByStatus(0);
			Gson gson = new Gson();
			out.print(gson.toJson(accounts));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Update account status
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		  var pStr = "http://localhost:1235/TheBank/transfers?";
		//		   pStr += "username=" + userParam
		//		         + "&accountid=" + accountid
		//		         + "&status=" + status;
		String username = request.getParameter("username");
		String accountid = request.getParameter("accountid");
		String status = request.getParameter("status");
		
		
		AccountBoImp abo = new AccountBoImp();
		try {
			Account account = abo.getAccountByAccountNumber(Integer.parseInt(accountid));
			abo.updateAccountStatus(account, Integer.parseInt(status));	
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
