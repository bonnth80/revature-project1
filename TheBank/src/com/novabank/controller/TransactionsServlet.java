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
import com.novabank.exception.BusinessException;
import com.novabank.to.Transaction;
import com.novabank.transactionBO.TransactionBoImp;

/**
 * Servlet implementation class TransactionsServlet
 */
@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();		
		Gson gson = new Gson();
		
		int accountNumber = Integer.parseInt(request.getParameter("accountnumber"));
		try {
			List<Transaction> transactions = new TransactionBoImp().getTransactionsByAccountId(accountNumber);
			String json = gson.toJson(transactions);
			out.print(json);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
