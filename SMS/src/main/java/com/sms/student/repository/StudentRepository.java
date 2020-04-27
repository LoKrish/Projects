package com.sms.student.repository;

import java.util.List;
import java.util.ArrayList;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import org.springframework.stereotype.Repository;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.sms.student.model.Student;

@Repository
public class StudentRepository {

	DatastoreService datastore;

	public StudentRepository() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	/*
	 * creating the student entity on the Google Datastore for creating the student
	 * we are using the registerNumber as the key
	 */

	public void createStudent(Student student) {
		Entity temporaryStudent = new Entity("students", student.getRegisterNumber());
		temporaryStudent.setProperty("firstName", student.getFirstName());
		temporaryStudent.setProperty("lastName", student.getLastName());
		temporaryStudent.setProperty("dateOfBirth", student.getDateOfBirth());
		temporaryStudent.setProperty("department", student.getDepartment());
		temporaryStudent.setProperty("email", student.getEmail());
		temporaryStudent.setProperty("registerNumber", student.getRegisterNumber());
		temporaryStudent.setProperty("password", student.getPassword());
		datastore.put(temporaryStudent);
	}

	/*
	 * Fetching the student using the registerNumber as the key
	 */

	public Student getStudent(int registerNumber, String password) throws EntityNotFoundException {
		Query query = new Query("students");
		PreparedQuery preparedQuery = datastore.prepare(query);
		for (Entity student1 : preparedQuery.asIterable()) {
			if (Integer.parseInt(student1.getProperty("registerNumber").toString()) == registerNumber
					&& (student1.getProperty("password").toString()).equals(password)) {
				String firstName = student1.getProperty("firstName").toString();
				String lastName = student1.getProperty("lastName").toString();
				String dateOfBirth = student1.getProperty("dateOfBirth").toString();
				String department = student1.getProperty("department").toString();
				String email = student1.getProperty("email").toString();
				Student student = new Student(firstName, lastName, registerNumber, department, dateOfBirth, email,
						password);
				return student;
			}
		}
		return null;
	}

	public boolean checkStudent(int registerNumber) {
		Query query = new Query("students");
		Filter registerNumberFilter = new FilterPredicate("registerNumber", FilterOperator.EQUAL, registerNumber);
		query.setFilter(registerNumberFilter);
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		if (entities.isEmpty()) {
			return false;
		}
		return true;
	}

	/*
	 * Fetching the details of the batchmates of the same department
	 */

	public ArrayList<Student> fetchBatchmates(Student student) {
		String department = student.getDepartment();
		Query query = new Query("students");
		Filter departmentFilter = new FilterPredicate("department", FilterOperator.EQUAL, department);
		query.setFilter(departmentFilter);
		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		ArrayList<Student> batchMates = new ArrayList<Student>();
		for (Entity temporaryStudent : entities) {
			String firstName = temporaryStudent.getProperty("firstName").toString();
			String lastName = temporaryStudent.getProperty("lastName").toString();
			String dateOfBirth = temporaryStudent.getProperty("dateOfBirth").toString();
			String email = temporaryStudent.getProperty("email").toString();
			int registerNumber = 0;
			String password = null;
			batchMates.add(new Student(firstName, lastName, registerNumber, department, dateOfBirth, email, password));
		}
		return batchMates;
	}

	/*
	 * Updating the Student details
	 */

	public void updateStudent(int registerNumber, Student student) throws EntityNotFoundException {
		Query query = new Query("students");
		PreparedQuery preparedQuery = datastore.prepare(query);
		for (Entity temporaryStudent : preparedQuery.asIterable()) {
			if (Integer.parseInt(temporaryStudent.getProperty("registerNumber").toString()) == registerNumber) {
				temporaryStudent.setProperty("firstName", student.getFirstName());
				temporaryStudent.setProperty("lastName", student.getLastName());
				temporaryStudent.setProperty("dateOfBirth", student.getDateOfBirth());
				temporaryStudent.setProperty("email", student.getEmail());
				temporaryStudent.setProperty("department", student.getDepartment());
				temporaryStudent.setProperty("registerNumber", student.getRegisterNumber());
				temporaryStudent.setProperty("password", student.getPassword());
				datastore.put(temporaryStudent);
			}
		}
	}
	/*
	 * Removing the user from the database
	 */

	public void deleteStudent(Student student) {
		Key studentKey = KeyFactory.createKey("students", student.getRegisterNumber());
		datastore.delete(studentKey);
	}
}
