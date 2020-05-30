package com.stockmanagement.model;

import java.time.LocalDate;

public class Sales {
	private LocalDate salesDate;
	private String productID;
	private String customerID; 
	private String productQuantity;
	private int amountEarned;
	
	public Sales(LocalDate salesDate, String productID, String customerID, String productQuantity, int amountEarned) {
		super();
		this.salesDate = salesDate;
		this.productID = productID;
		this.customerID = customerID;
		this.productQuantity = productQuantity;
		this.amountEarned = amountEarned;
	}

	public LocalDate getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(LocalDate salesDate) {
		this.salesDate = salesDate;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getAmountEarned() {
		return amountEarned;
	}

	public void setAmountEarned(int amountEarned) {
		this.amountEarned = amountEarned;
	}
	
}
