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
import com.novabank.exception.BusinessException;
import com.novabank.to.Transaction;
import com.novabank.transactionBO.TransactionBoImp;

/**
 * Servlet implementation class TransactionsServlet
 */
@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TransactionsServlet.class);
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("TransactionsServlet (doGet)");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();	
		int accountNumber = Integer.parseInt(request.getParameter("accountnumber"));	
		Gson gson = new Gson();
		log.info("TransactionsServlet (doGet): accountNumber=" + accountNumber);
		try {
			List<Transaction> transactions = new TransactionBoImp().getTransactionsByAccountId(accountNumber);
			String json = gson.toJson(transactions);
			log.info("TransactionsServlet (doGet): tractions=" + json);
			out.print(json);
		} catch (BusinessException e) {
			log.warn("TransactionsServlet (doGet): err=" + e);
		}
	}

}
