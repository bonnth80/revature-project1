package com.novabank.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.novabank.exception.BusinessException;
import com.novabank.to.Transaction;
import com.novabank.to.User;
import com.novabank.transactionBO.TransactionBoImp;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(WithdrawServlet.class);
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("WithdrawServlet (doPost)");
		response.setContentType("application/json");
		User user = (User)request.getSession().getAttribute("user");
		int accountId = Integer.parseInt(request.getParameter("accountid"));
		float amount = Float.parseFloat(request.getParameter("amount"));
		TransactionBoImp tbo = new TransactionBoImp();
		log.info("WithdrawServlet (doPost): accountid=" + accountId + " amount=" + amount);
		try {
			int newTransactionId = tbo.getMaxTransactionId() + 1;
			Transaction transaction = new Transaction(newTransactionId,
					accountId,
					(user.getFirstName() + " " + user.getLastName()).toUpperCase(),
					0,
					amount,
					new Date()
					);

			log.info("WithdrawServlet (doPost): transaction=" + transaction);
			tbo.addTransaction(transaction);
			
		} catch (BusinessException e) {
			log.warn("WithdrawServlet (doPost): err=" + e);
		}
	}

}
