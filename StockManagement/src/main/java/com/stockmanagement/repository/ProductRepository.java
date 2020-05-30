package com.stockmanagement.repository;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.stockmanagement.model.Manager;
import com.stockmanagement.model.Product;

public class ProductRepository {

	Objectify datastore;
	
	ProductRepository(){
		datastore=ObjectifyService.ofy();
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = datastore.load().type(Product.class).list();
		return products;
	}
	
	public List<Product> getProductsByManager(String managerID){
		Key<Manager> manager=Key.create(Manager.class,managerID);
		List<Product> products=datastore.load().type(Product.class).ancestor(manager).list();
		return products;
	}

	public String getProductID() {
		Product product = datastore.load().type(Product.class).list().get(0);
		if(product==null) {
			return null;
		}else {
			return product.getProductID();
		}
	}

	public void storeProduct(String managerID, Product product) {
		Key<Manager> manager=Key.create(Manager.class, managerID);
		datastore.save().entity(product).now();
	}

	public Product getproductByID(String productID) {
		return null;
	}

	public Product updateProduct(String productID, Product product) {
		return null;
	}

	public void deleteProduct(String productID) {
		
	}
}
