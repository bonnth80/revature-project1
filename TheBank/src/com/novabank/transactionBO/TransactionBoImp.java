package com.novabank.transactionBO;

import java.util.Date;
import java.util.List;

import com.novabank.exception.BusinessException;
import com.novabank.to.Transaction;
import com.novabank.transactionDAO.TransactionDaoImp;

public class TransactionBoImp implements TransactionBO {

	@Override
	public Transaction getTransactionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Transaction> getAllTransactions() throws BusinessException {
		return new TransactionDaoImp().getAllTransactions();
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(int accountId) throws BusinessException {
		// TODO Auto-generated method stub
		return new TransactionDaoImp().getTransactionsByAccountId(accountId);
	}

	@Override
	public List<Transaction> getTransactionsByActingParty(String actingParty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransactionByTransferId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTransaction(Transaction transaction) throws BusinessException {
		return new TransactionDaoImp().addTransaction(transaction);
	}

	@Override
	public int getMaxTransactionId() throws BusinessException {
		return new TransactionDaoImp().getMaxTransactionId();
	}

}
