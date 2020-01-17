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

import org.apache.log4j.Logger;

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
	private static Logger log = Logger.getLogger(TransferServlet.class);
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
		log.info("TransferServlet (doGet)");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        User user = (User)request.getSession().getAttribute("user");
		log.info("TransferServlet (doGet): user=" + user);
		try {
			List<Transfer> transfers = new TransferBoImp().getTransfersByUserId(user.getUserId());
			log.info("TransferServlet: (doGet): transfers = " + transfers);
			out.print(gson.toJson(transfers));
		} catch (BusinessException e) {
			log.warn("TransferServlet (doGet): err=" + e);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("TransferServlet (doPut)");
        response.setContentType("application/json");        
        PrintWriter out = response.getWriter();
		int transferid = Integer.parseInt(request.getParameter("transferid"));
		int newStatus = Integer.parseInt(request.getParameter("status"));
        Gson gson = new Gson();
		log.info("TransferServlet (doPut): transferid = " + transferid + " status = " + newStatus);
		try {
			TransferBoImp transferBo = new TransferBoImp();
			Transfer transfer = transferBo.getTransferById(transferid);
			if (newStatus == 1 || newStatus == 2) {
				boolean test = new TransferBoImp().updateTransferStatus(transfer, newStatus);
				String res = gson.toJson(transfer);
				log.info("TransferServlet (doPut): response=" + res);
				out.print(res);				
			} else {
				log.info("TransferServlet (doPut) invalid update request");
				out.print(gson.toJson("invalid update request"));
				
			}
			
		} catch (BusinessException e) {
			log.warn("TransferServlet (doPut): err=" + e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("TransferServlet (doPost)");
		response.setContentType("application/json");
		int accountSource = Integer.parseInt(request.getParameter("source"));
		int accountDestination = Integer.parseInt(request.getParameter("dest"));
		float amount = Float.parseFloat(request.getParameter("amount"));
		TransferBoImp tbo = new TransferBoImp();

		log.info("TransferServlet (doPost): source=" + accountSource + " destination=" + accountDestination + " amount=" + amount);
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

			log.info("TransferServlet (doPost): transfer posted=" + transfer);
			tbo.addNewTransfer(transfer);
			
		} catch (BusinessException e) {
			log.warn("TransferServlet (doPost): err=" + e);
		}
	}

}
