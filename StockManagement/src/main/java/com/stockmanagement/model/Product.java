package com.stockmanagement.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Product {
	@Id String productID;
	String productName;
	int quantity;
	int price;
	
	public Product(String productID, String productName, int quantity, int price) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
