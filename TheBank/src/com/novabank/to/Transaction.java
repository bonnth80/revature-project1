package com.novabank.to;

import java.util.Date;

public class Transaction {
	int transactionId;
	int accountId;
	String actingParty;
	float credit;
	float debit;
	Date transactionDate;
	int transferId;
	
	//Constructors
	public Transaction() {}

	public Transaction(int transactionId, int accountId, String actingParty, float credit, float debit,
			Date transactionDate, int transferId) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.actingParty = actingParty;
		this.credit = credit;
		this.debit = debit;
		this.transactionDate = transactionDate;
		this.transferId = transferId;
	}	

	public Transaction(int transactionId, int accountId, String actingParty, float credit, float debit,
			Date transactionDate) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.actingParty = actingParty;
		this.credit = credit;
		this.debit = debit;
		this.transactionDate = transactionDate;
		this.transferId = -1;
	}
	
	// Accessor and Mutators
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getActingParty() {
		return actingParty;
	}

	public void setActingParty(String actingParty) {
		this.actingParty = actingParty;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public float getDebit() {
		return debit;
	}

	public void setDebit(float debit) {
		this.debit = debit;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	
}
