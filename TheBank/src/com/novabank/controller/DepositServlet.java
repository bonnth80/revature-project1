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
 * Servlet implementation class DepositServlet
 */
@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DepositServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("DepositServlet (doPost)");
		response.setContentType("application/json");
		User user = (User)request.getSession().getAttribute("user");
		int accountId = Integer.parseInt(request.getParameter("accountid"));
		float amount = Float.parseFloat(request.getParameter("amount"));
		TransactionBoImp tbo = new TransactionBoImp();
		log.info("DepositServlet (doPost): accountid=" + accountId + " amount=" + amount);
		try {
			int newTransactionId = tbo.getMaxTransactionId() + 1;
			Transaction transaction = new Transaction(newTransactionId,
					accountId,
					(user.getFirstName() + " " + user.getLastName()).toUpperCase(),
					amount,
					0,
					new Date()
					);

			log.info("DepositServlet (doPost): transaction=" + transaction);
			tbo.addTransaction(transaction);
			
		} catch (BusinessException e) {
			log.warn("DepositServlet (doPost): err=" + e);
		}
	}

}
