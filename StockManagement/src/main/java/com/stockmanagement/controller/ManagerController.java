package com.stockmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stockmanagement.model.Manager;
import com.stockmanagement.model.Product;
import com.stockmanagement.repository.ManagerRepository;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	ManagerRepository managerRepository;
	
	@RequestMapping(method=RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Manager>> getAllManager(){
		List<Manager> managers= managerRepository.getAllManager();
		return new ResponseEntity<List<Manager>>(managers,HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Manager createManager(@RequestBody Manager manager) {
		manager.setManagerID(generateManagerID());
		managerRepository.storeManager(manager);
		return manager;
	}


	private String generateManagerID() {
		String existingManagerID = managerRepository.getManagerID();
		if (existingManagerID.equals(null)) {
			return "PR0000";
		} else {
			String code = existingManagerID.substring(0, 2);
			int index = Integer.parseInt(existingManagerID.substring(2));
			index++;
			return code + index;
		}
	}
	
}
