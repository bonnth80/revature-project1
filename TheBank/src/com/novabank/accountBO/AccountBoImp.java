package com.novabank.accountBO;

import java.util.Date;
import java.util.List;

import com.novabank.accountDAO.AccountDAO;
import com.novabank.accountDAO.AccountDaoImp;
import com.novabank.exception.BusinessException;
import com.novabank.to.Account;

public class AccountBoImp implements AccountBO {

	@Override
	public Account getAccountByAccountNumber(int accountNumber) throws BusinessException {
		AccountDAO ad = new AccountDaoImp();
		
		return ad.getAccountByAccountNumber(accountNumber);
	}
	
	@Override
	public boolean accountExists(int accountNumber) throws BusinessException {
		return new AccountDaoImp().accountExists(accountNumber);
	}
	
	@Override
	public int getMaxAccountNumber() throws BusinessException {		
		return new AccountDaoImp().getMaxAccountNumber();
	}

	@Override
	public List<Account> getAccountsByUserId(int userId) throws BusinessException {
		return new AccountDaoImp().getAccountsByUserId(userId);
	}
	
	@Override
	public int getPendingApprovalCount() throws BusinessException {
		AccountDAO act = new AccountDaoImp();
		return act.getPendingApprovalCount();
	}

	@Override
	public List<Account> getAccountsByCreationDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByStatus(int status) throws BusinessException {
		AccountDAO ad = new AccountDaoImp();
		List<Account> accountList = ad.getAccountsByStatus(status);
		return accountList;
	}

	@Override
	public boolean addNewAccount(Account account) throws BusinessException {
		return new AccountDaoImp().addNewAccount(account);
	}

	@Override
	public boolean updateAccountStatus(Account account, int status) throws BusinessException {
		AccountDAO ad = new AccountDaoImp();
		return ad.updateAccountStatus(account, status);
	}
	
	//Methods
	public String getUserFirstName(Account account) throws BusinessException {
		return new AccountDaoImp().getUserFirstName(account);
	}

	public String getUserLastName(Account account) throws BusinessException {
		return new AccountDaoImp().getUserLastName(account);
	}

	@Override
	public List<Account> getAccountsByUserName(String firstName, String lastName) throws BusinessException {
		return new AccountDaoImp().getAccountsByUserName(firstName, lastName);
	}


}
