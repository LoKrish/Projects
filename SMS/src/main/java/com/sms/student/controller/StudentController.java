package com.sms.student.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.sms.student.model.Student;
import com.sms.student.repository.StudentRepository;

@RestController
@SessionAttributes("student")
public class StudentController extends HttpServlet {

	/**
	 * this serial version uid is to deserialize the object. as the serial version
	 * uid will generate the id based on the compiler.
	 */
	private static final long serialVersionUID = 1L;

	@ModelAttribute("student")
	public Student student() {
		return new Student();
	}

	StudentRepository studentRepository = new StudentRepository();
	
	/*
	 *  Redirecting to the login page
	 */
	@GetMapping(value = "/loginPage")
	public void loginPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("Login.html");
	}

	/*
	 *  Redirecting to the SignUp
	 */
	@GetMapping(value = "/signUpPage")
	public void signUpPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("SignUp.html");
	}

	/* 
	 *  Login checking the username and password and returning the user if both matches
	 *  else redirecting to the login page
	 */
	
	@PostMapping(value = "/userEntry")
	public ModelAndView userlogin(@RequestParam("registerNumber") int registerNumber,
			@RequestParam("password") String password, @SessionAttribute("student") Student currentUser)
			throws EntityNotFoundException {
		Student student = studentRepository.getStudent(registerNumber, password);
		if (student == null) {
			System.out.println("Error");

		}
		currentUser.setRegisterNumber(student.getRegisterNumber());
		currentUser.setFirstName(student.getFirstName());
		currentUser.setLastName(student.getLastName());
		currentUser.setDepartment(student.getDepartment());
		currentUser.setDateOfBirth(student.getDateOfBirth());
		currentUser.setEmail(student.getEmail());
		currentUser.setPassword(student.getPassword());
		System.out.print(currentUser.getFirstName());
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("Dashboard.jsp");
		modelView.addObject("student", currentUser);
		return modelView;

	}

	/*
	 *  Accepting the input from the user and checking whether the user exists already in the database  
	 *  if exists redirecting to the login page 
	 *  else storing it in database and redirecting the user to the loginpage
	 */
	@PostMapping(value = "/userSignup")
	public ModelAndView userSignup(@ModelAttribute("student") Student student) {
		System.out.println(student.getFirstName());
		if (!studentRepository.checkStudent(student.getRegisterNumber())) {
			studentRepository.createStudent(student);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("Login.html");
			return modelView;
		}
		return null;
	}

	/*
	 * To view the userDetails of the user
	 */

	@GetMapping(value = "/userDetails")
	public ModelAndView userDetails(@SessionAttribute("student") Student student) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}

	/*
	 * To view the batchmates of the student || We are filtering this through the
	 * department of the student
	 */

	@GetMapping(value = "/viewBatchmates")
	public ModelAndView viewBatchMates(@SessionAttribute("student") Student student) {
		ArrayList<Student> batchmates = studentRepository.fetchBatchmates(student);
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("ViewBatchMates.jsp");
		modelView.addObject("batchmates", batchmates);
		return modelView;
	}

	/*
	 * user Logging out from web app The session object is set to null and
	 * redirecting to homepage.html
	 */

	@GetMapping(value = "/dashboard")
	public ModelAndView dashboard(@SessionAttribute("student") Student student) {
		System.out.println(student.getRegisterNumber());
		ModelAndView modelView = new ModelAndView();
		if(student.getRegisterNumber()==0) {
			modelView.setViewName("Login.html");
			return modelView;
		}
		modelView.setViewName("Dashboard.jsp");
		modelView.addObject("student", student);
		return modelView;
	}

	@GetMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("student") Student student) throws ServletException, IOException {
		response.sendRedirect("Homepage.html");
	}
}