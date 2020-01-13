package com.novabank.to;

import java.util.Date;

import com.novabank.accountBO.AccountBoImp;
import com.novabank.exception.BusinessException;

public class Account {
	int accountNumber;
	int userId;
	Date creationDate;
	int status;
	float startingBalance;
	float availableBalance;
	
	// Constructors
	public Account() {};
	
	public Account(int accountNumber, int userId, Date creationDate, int status, float startingBalance,
			float availableBalance) {
		super();
		this.accountNumber = accountNumber;
		this.userId = userId;
		this.creationDate = creationDate;
		this.status = status;
		this.startingBalance = startingBalance;
		this.availableBalance = availableBalance;
	}
	
	// Accessor and Mutators
	public void setAvailableBalance(float availableBalance) {
		this.availableBalance = availableBalance;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getStartingBalance() {
		return startingBalance;
	}
	public void setStartingBalance(int startingBalance) {
		this.startingBalance = startingBalance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public float getAvailableBalance() {
		return availableBalance;
	}
	
	// Methods
	public String getUserFirstName() throws BusinessException {
		return new AccountBoImp().getUserFirstName(this);
	}
	
	public String getUserLastName() throws BusinessException {
		return new AccountBoImp().getUserLastName(this);
	}
	
}
