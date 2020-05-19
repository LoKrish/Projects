package com.sms.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sms.student.repository.StudentRepository;

@RestController

public class LoginController {
	
	StudentRepository studentRepository=new StudentRepository();
	
	@GetMapping(value = "/")
	public void homepage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session= request.getSession(false);
		if (session == null) {
			System.out.println("at homepage");
			response.sendRedirect("Homepage.html");
		} else {
			response.sendRedirect("/dashboard");
		}
	}

	@GetMapping(value = "/login")
	public void loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session= request.getSession(false);
		if (session == null) {
			response.sendRedirect("Login.jsp");
		} else {
			response.sendRedirect("/dashboard");
		}
	}

	@GetMapping(value = "/signUp")
	public void signUpPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session= request.getSession(false);
		if (session == null) {
			response.sendRedirect("SignUp.jsp");
		} else {
			response.sendRedirect("/dashboard");
		}
	}
	
	/*
	 * Used to check whether the email is already present in the database and return
	 * success or failure.
	 */
	@GetMapping(value = "/validate/email={emailId:.+}")
	public void validateEmail(@PathVariable(value = "emailId") String email, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("request recieved");
		System.out.println(request.getParameter("email"));
		System.out.println(email);
		if (studentRepository.validateEmail(email)) {
			response.setContentType("text");
			response.setHeader("Cache-control", "no-cache");
			response.getWriter().write("failure");
			System.out.println("failure");
		} else {
			response.setContentType("text");
			response.setHeader("Cache-control", "no-cache");
			response.getWriter().write("success");
			System.out.println("success");
		}
	}	
	
	/*
	 * After the user logout we are making the session invalidate and making the
	 * register number of the user to zero
	 */
	@GetMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect("/");
	}
}
