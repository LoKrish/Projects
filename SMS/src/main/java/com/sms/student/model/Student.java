package com.sms.student.model;

import java.io.Serializable;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Entity(name="students")
@Component
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2672769878105289164L;
	private String firstName;
	private String lastName;
	
	@Id
	private int registerNumber;
	private String department;
	private String dateOfBirth;
	private String email;
	private String password;

	public Student() {
		
	}
	public Student(String firstName, String lastName, int registerNumber, String department, String dateOfBirth,
			String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.registerNumber = registerNumber;
		this.department = department;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getRegisterNumber() {
		return registerNumber;
	}
	
	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

}
