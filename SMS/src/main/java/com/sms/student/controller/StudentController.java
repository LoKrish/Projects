package com.sms.student.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class StudentController extends HttpServlet{
	
	/**
	 *  this serial version uid is to deserialize the object. as the serial version uid will generate the 
	 *  id based on the compiler.
	 */
	private static final long serialVersionUID = 1L;
	
	@ModelAttribute("student")
	public Student student() {
		return new Student();
	}
	StudentRepository studentRepository=new StudentRepository();

	@GetMapping(value="/login")
	public void loginPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("Login.html");
	}
	
	@GetMapping(value="/signUp")
	public void signUpPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("SignUp.html");
	}
	
	@PostMapping(value="/userEntry")
	public void userlogin(HttpServletRequest request,HttpServletResponse response,@RequestParam("registerNumber") int registerNumber,@RequestParam("password") String password,@SessionAttribute("student") Student currentUser) throws EntityNotFoundException, ServletException, IOException {
		Student student=studentRepository.getStudent(registerNumber, password);
		System.out.println("hello");
		if(student==null) {
			System.out.println("Error");
		}
		else {
			currentUser.setRegisterNumber(student.getRegisterNumber());
			currentUser.setFirstName(student.getFirstName());
			currentUser.setLastName(student.getLastName());
			currentUser.setDepartment(student.getDepartment());
			currentUser.setDateOfBirth(student.getDateOfBirth());
			currentUser.setEmail(student.getEmail());
			currentUser.setPassword(student.getPassword());
			System.out.print(currentUser.getFirstName());
			response.sendRedirect("LoginHomepage.html");
		}
		
	}
	

	@PostMapping(value="/userSignup")
	public void userSignup(@ModelAttribute("student") Student student,HttpServletResponse response) throws EntityNotFoundException, IOException {
		System.out.println(student.getFirstName());
		if(!studentRepository.checkStudent(student.getRegisterNumber())) {
			studentRepository.createStudent(student);
			response.sendRedirect("Login.html");
		}else {
			// SignUP error
		}
	}
	
	
	/*
	 *  To view the userDetails of the user 
	 */
	
	@GetMapping(value="/userDetails")
	public ModelAndView userDetails(HttpServletRequest request,HttpServletResponse response,@SessionAttribute("student") Student student) throws ServletException, IOException {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	
	/*
	 *  To view the batchmates of the student || We are filtering this through the department of the student
	 */
	
	@GetMapping(value="/viewBatchmates")
	public ModelAndView viewBatchMates(HttpServletRequest request,HttpServletResponse response,@SessionAttribute("student") Student student) throws ServletException, IOException {
		ArrayList<Student> batchmates = studentRepository.fetchBatchmates(student);
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("ViewBatchMates.jsp");
		modelView.addObject("batchmates", batchmates);
		return modelView;
	}
	
	/*
	 *  user Logging out from web app
	 *  The session object is set to null and redirecting to homepage.html
	 */
	
	@GetMapping(value="/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.setAttribute("student", null );
			request.setAttribute("LogoutMessage", "You have been logged out");
			response.sendRedirect("Homepage.html");
		}
	}
}