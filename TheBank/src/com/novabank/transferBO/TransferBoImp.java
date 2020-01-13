package com.novabank.transferBO;

import java.util.Date;
import java.util.List;

import com.novabank.exception.BusinessException;
import com.novabank.to.Transfer;
import com.novabank.transferDAO.TransferDaoImp;

public class TransferBoImp implements TransferBO {

	@Override
	public Transfer getTransferById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxTransferId() throws BusinessException {
		return new TransferDaoImp().getMaxTransferId();
	}
	
	@Override
	public int getTransferCountByUserId(int userId) throws BusinessException {
		return  new TransferDaoImp().getTransferCountByUserId(userId);
	}
	
	@Override
	public List<Transfer> getTransfersBySourceAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByDestinationAccount(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByRequestDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> getTransfersByApprovalDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewTransfer(Transfer transfer) throws BusinessException {
		
		return new TransferDaoImp().addNewTransfer(transfer);
	}

	@Override
	public List<Transfer> getTransfersByUserId(int userId) throws BusinessException {
		return new TransferDaoImp().getTransfersByUserId(userId);
	}

	@Override
	public boolean updateTransferStatus(Transfer transfer, int status) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}
