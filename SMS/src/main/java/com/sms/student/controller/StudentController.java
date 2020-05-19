package com.sms.student.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.sms.student.model.Student;
import com.sms.student.repository.StudentRepository;

@RestController
public class StudentController extends HttpServlet {

	/**
	 * this serial version uid is to deserialize the object. as the serial version
	 * uid will generate the id based on the compiler.
	 */
	private static final long serialVersionUID = 1L;

	StudentRepository studentRepository = new StudentRepository();

	/*
	 * Login checking the username and password and returning the user if both
	 * matches else redirecting to the login page
	 */

	@PostMapping(value = "/userEntry")
	public void userlogin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, HttpServletResponse response)
			throws EntityNotFoundException, IOException {
		System.out.println(password);
		String salt = "SHA1PRNG";
		String securedPassword = PasswordUtils.generateSecurePassword(password, salt);
		System.out.println(securedPassword);
		Student student = studentRepository.getStudent(email, securedPassword);
		System.out.println(email);
		if (student == null) {
			response.setContentType("text");
			response.setHeader("Cache-control", "no-cache");
			response.getWriter().write("failure");
			System.out.println(email);
		} else {
			session.setAttribute("student", student);
			System.out.print(student.getFirstName());
			response.setContentType("text");
			response.setHeader("Cache-control", "no-cache");
			response.getWriter().write("success");
		}
	}

	/*
	 * Accepting the input from the user and checking whether the user exists
	 * already in the database if exists redirecting to the login page else storing
	 * it in database and redirecting the user to the loginpage
	 */

	@PostMapping(value = "/userSignup")
	public void userSignup(@ModelAttribute("student") Student student, HttpServletResponse response)
			throws IOException {
		int registerNumber = generateRegisterNumber(student.getDepartment());
		student.setRegisterNumber(registerNumber);
		String salt = "SHA1PRNG";
		System.out.println(student.getPassword());
		String securedPassword = PasswordUtils.generateSecurePassword(student.getPassword(), salt);
		student.setPassword(securedPassword);
		System.out.println(securedPassword);
		studentRepository.createStudent(student);
		response.setContentType("text");
		response.setHeader("Cache-control", "no-cache");
		String number = registerNumber + "";
		response.getWriter().write(number);
		System.out.println(registerNumber);
	}

	/*
	 * To view the userDetails of the user
	 */

	@GetMapping(value = "/editUserDetails")
	public ModelAndView userDetails(HttpSession session) {
		Student student=(Student) session.getAttribute("student");
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("Userdetails.jsp");
		modelView.addObject("student", student);
		return modelView;
	}

	/*
	 * To view the batchmates of the student || We are filtering this through the
	 * department of the student
	 */

	@PostMapping(value = "/userUpdateDetails")
	public void editUserDetails(@ModelAttribute("student") Student temporaryStudent,HttpSession session, HttpServletResponse response)
			throws IOException, EntityNotFoundException {
		Student student=(Student) session.getAttribute("student");
		student.setDateOfBirth(temporaryStudent.getDateOfBirth());
		student.setFirstName(temporaryStudent.getFirstName());
		student.setLastName(temporaryStudent.getLastName());
		student.setEmail(temporaryStudent.getEmail());
		studentRepository.updateStudent(student.getRegisterNumber(), student);
		response.sendRedirect("/dashboard");
	}

	/*
	 * Generates registerNumber is
	 */
	public int generateRegisterNumber(String department) {
		int registerNumber = studentRepository.getRegisterNumber(department);
		int registerCode = (department.equals("CSE")) ? 222
				: (department.equals("MECH")) ? 122
						: (department.equals("EEE")) ? 522 : (department.equals("ECE")) ? 422 : 322;
		if (registerNumber == 0) {
			String register = String.valueOf(registerCode) + "000";
			return Integer.parseInt(register);
		} else {
			return registerNumber + 1;
		}
	}

	@GetMapping(value = "/viewBatchmates")
	public ModelAndView viewBatchMates(HttpSession session) {
		Student student=(Student) session.getAttribute("student");
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
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session=request.getSession();
		Student student=(Student) session.getAttribute("student");
		//System.out.println(student.getRegisterNumber());
		System.out.print("hello");
		ModelAndView modelView = new ModelAndView();
		if (student==null) {
			response.sendRedirect("Login.jsp");
			return null;
		} else {
			student.setImage(studentRepository.getImage(student.getRegisterNumber()));
			modelView.setViewName("Dashboard.jsp");
			modelView.addObject("student", student);
			return modelView;
		}
	}

	@GetMapping(value = "/uploadProfilePicture")
	public ModelAndView uploadPage() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("ImageUpload.jsp");
		return modelView;

	}

	/*
	 * Storing the image in the Blobstore and storing the image url in the datastore
	 */
	@PostMapping(value = "/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws EntityNotFoundException, IOException, ServletException {
		Student student=(Student) session.getAttribute("student");
		Part part=request.getPart("image");
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		ObjectOutputStream outputStream=new ObjectOutputStream(byteArrayOutputStream);
		outputStream.writeObject(part);
		outputStream.flush();
		byte[] data=byteArrayOutputStream.toByteArray();
		studentRepository.uploadImage(student.getRegisterNumber(), data);
		response.sendRedirect("/dashboard");

	}
}