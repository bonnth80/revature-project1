package com.novabank.accountBO;

import java.util.Date;
import java.util.List;

import com.novabank.exception.BusinessException;
import com.novabank.to.Account;

public interface AccountBO {
	Account getAccountByAccountNumber(int accountNumber) throws BusinessException;
	List<Account> getAccountsByUserId(int userId) throws BusinessException;
	List<Account> getAccountsByUserName(String firstName, String lastName) throws BusinessException;
	boolean accountExists(int accountNumber) throws BusinessException;
	int getMaxAccountNumber() throws BusinessException;
	int getPendingApprovalCount() throws BusinessException;
	List<Account> getAccountsByCreationDate(Date date);
	List<Account> getAccountsByStatus(int status) throws BusinessException;
	
	
	boolean addNewAccount(Account account) throws BusinessException;
	boolean updateAccountStatus(Account account, int status) throws BusinessException;
}
