package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
import com.novabank.to.User;

/**
 * Servlet implementation class ApplyServlet
 */
@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ApplyServlet.class);  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("ApplyServlet (doPost)");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();		
		Gson gson = new Gson();
		User user = (User)request.getSession().getAttribute("user");
		AccountBoImp abo = new AccountBoImp();
		log.info("ApplyServlet (doPost): user=" + user);
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

			log.info("ApplyServlet (doPost): account=" + account);
			log.info("ApplyServlet (doPost): New account processed");
			abo.addNewAccount(account);
		} catch (BusinessException e) {
			log.warn("ApplyServlet (doPost): err=" + e);
		}
	}
}
