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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();		
		Gson gson = new Gson();
		User user = (User)request.getSession().getAttribute("user");
		try {
			List<Account> accounts = new AccountBoImp().getAccountsByUserId(user.getUserId());
			int transferCount = new TransferBoImp().getTransferCountByUserId(user.getUserId());
			List<Object> resData = new ArrayList<>();
			resData.add(new Integer(transferCount));
			resData.add(accounts);
			resData.add(user.getFirstName());
			out.print(gson.toJson(resData));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
