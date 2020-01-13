package com.novabank.to;

import java.util.Date;

public class Transfer {
	int transferId;
	float amount;
	int source;
	int destination;
	int status;
	Date requestDate;
	Date responseDate;
	
	// Constructors
	public Transfer() {};
	
	public Transfer(int transferId, float amount, int source, int destination, int status, Date requestDate,
			Date responseDate) {
		super();
		this.transferId = transferId;
		this.amount = amount;
		this.source = source;
		this.destination = destination;
		this.status = status;
		this.requestDate = requestDate;
		this.responseDate = responseDate;
	}
	
	// Accessor and Mutators
	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}	
	
}
