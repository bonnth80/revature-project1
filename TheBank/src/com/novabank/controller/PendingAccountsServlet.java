package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
	private static Logger log = Logger.getLogger(PendingAccountsServlet.class);
       
    // Get pending accounts
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("PendingAccountsServlet (doGet)");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		try {
			List<Account> accounts = new AccountBoImp().getAccountsByStatus(0);
			Gson gson = new Gson();
			log.info("PendingAccountsServlet (doGet): returning pending accounts");
			out.print(gson.toJson(accounts));
		} catch (BusinessException e) {
			log.warn("PendingAccountsServlet (doGet): err=" + e);
		}
	}

	// Update account status
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("PendingAccountsServlet (doPut)");
		
		String username = request.getParameter("username");
		String accountid = request.getParameter("accountid");
		String status = request.getParameter("status");
		AccountBoImp abo = new AccountBoImp();
		
		log.info("PendingAccountsServlet (doPut): username=" + username + " accountid=" + accountid + " status=" + status);
		try {
			Account account = abo.getAccountByAccountNumber(Integer.parseInt(accountid));
			log.info("PendingAccountsServlet (doPut): returning " + account);
			abo.updateAccountStatus(account, Integer.parseInt(status));	
		} catch (BusinessException e) {
			log.warn("PendingAccountsServlet (doPut): err=" + e);
		}
	}

}
