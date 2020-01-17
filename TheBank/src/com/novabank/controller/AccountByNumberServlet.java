package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class AccountByNumberServlet
 */
@WebServlet("/accountbynumber")
public class AccountByNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AccountByNumberServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("AccountByNumberServlet (doGet)");
		response.setContentType("application/json");
		int accountid = Integer.parseInt(request.getParameter("accountid"));
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		log.info("AccountByNumberServlet (doGet): accountid=" + accountid);
		
		try {
			Account account = new AccountBoImp().getAccountByAccountNumber(accountid);
			String res = gson.toJson(account);
			log.info("AccountByNumberServlet (doGet): res=" + res);
			out.print(res);

		} catch (BusinessException e) {
			log.warn("AccountByNumberServlet (doGet): err=" + e);
		}
	}

}
