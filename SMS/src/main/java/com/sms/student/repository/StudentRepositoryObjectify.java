package com.sms.student.repository;

import com.sms.student.model.Student;

import java.util.ArrayList;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class StudentRepositoryObjectify {

	Objectify datastore = ObjectifyService.ofy();

	public boolean validateEmail(String email) {
		return datastore.load().type(Student.class).filter("email", email).first().now() != null;
	}

	public int getRegisterNumber(String department) {
		Student student = datastore.load().type(Student.class).filter("department", department).order("-registerNumber")
				.first().now();
		if (student != null) {
			return student.getRegisterNumber();
		} else {	
			return 0;
		}
	}

	public void createStudent(Student student) {
		datastore.save().entity(student).now();
	}

	public Student getStudent(String email, String password) {
		Student student = datastore.load().type(Student.class).filter("email", email).first().now();
		if (student.getPassword().equals(password)) {
			return student;
		} else {
			return null;
		}
	}

	public ArrayList<Student> fetchBatchmates(Student student) {
		ArrayList<Student> students = (ArrayList<Student>) datastore.load().type(Student.class)
				.filter("department", student.getDepartment()).list();
		return students;
	}
}
