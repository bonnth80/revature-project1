package com.novabank.transactionBO;

import java.util.Date;
import java.util.List;

import com.novabank.exception.BusinessException;
import com.novabank.to.Transaction;

public interface TransactionBO {
	Transaction getTransactionById(int id);
	int getMaxTransactionId() throws BusinessException;
	List<Transaction> getAllTransactions() throws BusinessException;
	List<Transaction> getTransactionsByAccountId(int accountId) throws BusinessException;
	List<Transaction> getTransactionsByActingParty(String actingParty);
	List<Transaction> getTransactionByDate(Date date);
	Transaction getTransactionByTransferId(int id);
	
	boolean addTransaction(Transaction transaction) throws BusinessException;
}
