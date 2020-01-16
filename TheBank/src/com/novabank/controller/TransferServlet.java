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
import com.novabank.to.Transfer;
import com.novabank.to.User;
import com.novabank.transferBO.TransferBoImp;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/transfers")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("application/jason");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        User user = (User)request.getSession().getAttribute("user");
		try {
			List<Transfer> transfers = new TransferBoImp().getTransfersByUserId(user.getUserId());
			out.print(gson.toJson(transfers));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
