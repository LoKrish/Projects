package com.stockmanagement.repository;

import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.stockmanagement.model.Manager;

public class ManagerRepository {

	Objectify datastore;

	ManagerRepository() {
		datastore = ObjectifyService.ofy();
	}
	
	public List<Manager> getAllManager(){
		List<Manager> managers= datastore.load().type(Manager.class).list();
		return managers;
	}

	public String getManagerID() {
		Manager manager=datastore.load().type(Manager.class).list().get(0);
		if(manager==null) {
			return null;
		}
		else {
			return manager.getManagerID();
		}
	}

	public void storeManager(Manager manager) {
		
	}
}
