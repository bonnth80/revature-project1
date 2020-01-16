package com.novabank.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		User user = (User)request.getSession().getAttribute("user");
		int accountId = Integer.parseInt(request.getParameter("accountid"));
		float amount = Float.parseFloat(request.getParameter("amount"));
		TransactionBoImp tbo = new TransactionBoImp();
		
		try {
			int newTransactionId = tbo.getMaxTransactionId() + 1;
			Transaction transaction = new Transaction(newTransactionId,
					accountId,
					(user.getFirstName() + " " + user.getLastName()).toUpperCase(),
					0,
					amount,
					new Date()
					);
			tbo.addTransaction(transaction);
			//System.out.println("Withdraw successful");
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
