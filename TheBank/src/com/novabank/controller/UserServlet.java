package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.novabank.to.User;
import com.novabank.transferBO.TransferBoImp;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserServlet.class);
	
	// Get all initial data necessary to display customer page and return it to the requestor
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("UserServlet (doGet)");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();		
		Gson gson = new Gson();
		User user = (User)request.getSession().getAttribute("user");
		log.info("UserServlet (doGet): user=" + user);
		try {
			List<Account> accounts = new AccountBoImp().getActiveAccountsByUserId(user.getUserId());
			int transferCount = new TransferBoImp().getTransferCountByUserId(user.getUserId());
			List<Object> resData = new ArrayList<>();
			resData.add(new Integer(transferCount));
			resData.add(accounts);
			resData.add(user.getFirstName());
			log.info("UserServlet (doGet): resDat=" + resData);
			out.print(gson.toJson(resData));
		} catch (BusinessException e) {
			log.warn("UserServlet (doGet): err=" + e);
		}
	}

}
