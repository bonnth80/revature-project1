package com.novabank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TransferServlet handling post request");
        response.setContentType("application/jason");        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
		try {
			TransferBoImp transferBo = new TransferBoImp();
			int transferid = Integer.parseInt(request.getParameter("transferid"));
			System.out.println("Getting transfer for id: " + transferid);
			Transfer transfer = transferBo.getTransferById(transferid);
			System.out.println("... " + transfer);
			int newStatus = Integer.parseInt(request.getParameter("status"));
			System.out.println(newStatus);
			if (newStatus == 1 || newStatus == 2) {
				System.out.println("in transfer zone for transferid: " + transfer.getTransferId());
				boolean test = new TransferBoImp().updateTransferStatus(transfer, newStatus);
				System.out.println("test: " + test);
				out.print(gson.toJson(transfer));				
			} else {
				System.out.println("Something went wrong");
				out.print(gson.toJson("invalid update request"));
				
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		int accountSource = Integer.parseInt(request.getParameter("source"));
		int accountDestination = Integer.parseInt(request.getParameter("dest"));
		float amount = Float.parseFloat(request.getParameter("amount"));
		TransferBoImp tbo = new TransferBoImp();
		
		try {
			int newTransferId = tbo.getMaxTransferId() + 1;
			Transfer transfer = new Transfer(
					newTransferId,
					amount,
					accountSource,
					accountDestination,
					0,
					new Date(),
					null
					);
			tbo.addNewTransfer(transfer);
			System.out.println("Transfer posted.");
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
