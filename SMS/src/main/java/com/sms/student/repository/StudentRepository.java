package com.sms.student.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
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
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
/*
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
*/
import com.sms.student.model.Student;

@Repository
public class StudentRepository {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();;
	/*Storage storage;
	StorageOptions storageOptions;
	Bucket bucket;
	{
		
		try {
			storageOptions = StorageOptions.newBuilder().setProjectId("lokeshproject2203")
					.setCredentials(GoogleCredentials.fromStream(new FileInputStream(
							"C:\\Users\\user.FULL283-WIN.000.001\\Desktop\\lokeshproject2203-fbcf01463dec.json"))) .build();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		storage = storageOptions.getService(); 
		//storage = StorageOptions.getDefaultInstance().getService();
		createBucket();
	} 

	void createBucket() {
		Bucket temporaryBucket = storage.get("lokeshproject2203.appspot.com", Storage.BucketGetOption.fields());
		if (temporaryBucket == null) {
			bucket = storage.create(BucketInfo.of("lokeshproject2203.appspot.com"));
		} else {
			bucket = temporaryBucket;
		}
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
		temporaryStudent.setProperty("image", null);
		datastore.put(temporaryStudent);
	}

	/*
	 * Fetching the student using the registerNumber as the key
	 */

	public Student getStudent(String email, String password) throws EntityNotFoundException {
		Query query = new Query("students");
		PreparedQuery preparedQuery = datastore.prepare(query);
		for (Entity student1 : preparedQuery.asIterable()) {
			if (student1.getProperty("email").toString().equals(email)
					&& (student1.getProperty("password").toString()).equalsIgnoreCase(password)) {
				String firstName = student1.getProperty("firstName").toString();
				String lastName = student1.getProperty("lastName").toString();
				String department = student1.getProperty("department").toString();
				int registerNumber = Integer.parseInt(student1.getProperty("registerNumber").toString());
				Student student = new Student(firstName, lastName, registerNumber, department, email, password);
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
			String email = temporaryStudent.getProperty("email").toString();
			int registerNumber = 0;
			String password = null;
			batchMates.add(new Student(firstName, lastName, registerNumber, department, email, password));
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

	public int getRegisterNumber(String department) {
		Query query = new Query("students");
		Filter departmentFilter = new FilterPredicate("department", FilterOperator.EQUAL, department);
		query.setFilter(departmentFilter).addSort("registerNumber", SortDirection.DESCENDING);
		PreparedQuery preparedQuery = datastore.prepare(query);
		for (Entity temporaryStudent : preparedQuery.asIterable()) {
			return Integer.parseInt(temporaryStudent.getProperty("registerNumber").toString());
		}
		return 0;
	}

	/*
	 * Creating the upload url to store the image
	 */

	public void addStudentImage(int registerNumber, Student student, String image) throws EntityNotFoundException {
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
				temporaryStudent.setProperty("image", image);
				datastore.put(temporaryStudent);
			}
		}
	}

	public boolean validateEmail(String email) {
		Query query = new Query("students");
		PreparedQuery preparedQuery = datastore.prepare(query);
		for (Entity temporaryStudent : preparedQuery.asIterable()) {
			if (temporaryStudent.getProperty("email").toString().equals(email)) {
				return true;
			}
		}
		return false;
	}

	/* public String uploadImage(int registerNumber, byte[] data) {
		BlobId blobId = BlobId.of("students", String.valueOf(registerNumber));
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
		storage.create(blobInfo, data);
		return null;
	}

	public String getImage(int registerNumber) {
		BlobId blobId = BlobId.of("student", String.valueOf(registerNumber));
		Blob blob = storage.get(blobId);
		if (blob != null) {
			int urlLifeTime = 86400;
			TimeUnit seconds = TimeUnit.SECONDS;
			URL servingUrl = blob.signUrl(urlLifeTime, seconds);
			String url = servingUrl.toString();
			return url;
		}
		return null;
	} */
}
