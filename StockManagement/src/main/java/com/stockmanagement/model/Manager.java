package com.stockmanagement.model;

import java.util.List;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Manager {
	@Id String managerID;
	String name;
	String email;
	String password;
	String amountEarned;
	List<Product> products;
	List<Sales> sales;
	
	public Manager(String managerID, String name, String email, String password, String amountEarned,
			List<Product> products, List<Sales> sales) {
		super();
		this.managerID = managerID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.amountEarned = amountEarned;
		this.products = products;
		this.sales = sales;
	}
	
	public String getManagerID() {
		return managerID;
	}
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAmountEarned() {
		return amountEarned;
	}
	public void setAmountEarned(String amountEarned) {
		this.amountEarned = amountEarned;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Sales> getSales() {
		return sales;
	}
	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}
	
}
