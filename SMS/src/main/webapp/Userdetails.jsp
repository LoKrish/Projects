<%@page import="com.sms.student.model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Sign UP</title>
	<link rel="stylesheet" type="text/css" href="SignUp.css">
	<link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<div class="body">
		<div class="login">
			<h2 class="loginTitle"> User Details </h2>
			<form id="SignupForm" action="/userSignup" method="post">
			<div class="text">
				<input type="text" name="firstName" value='<c:out value="${student.getFirstName()}"></c:out>'  />
			</div>
			<div class="text">
				<input type="text" name="lastName"   value='<c:out value="${student.getLastName()} "></c:out>' />
			</div>
			<div class="text">
				<input list="department" name="department" value='<c:out value="${student.getDepartment()}"></c:out>' />
				<datalist id="department">
  				<option value="CSE">
  				<option value="MECH">
  				<option value="CIVIL">
  				<option value="ECE">
  				<option value="EEE">
				</datalist>
			</div>
			<div class="text">
				<input type="date" name="dateOfBirth" value='<c:out value="${student.getDateOfBirth()} "></c:out>' />
			</div>
			<div class="text">
				<input type="email" name="email" value='<c:out value="${student.getEmail()}"></c:out>'  />
			</div>
			<div class="text">
				<input type="password" name="password"  placeholder="password" >
			</div>
			<div class="text">
				<input type="password" name="confirmPassword" placeholder="Confirm Password">
			</div>
			<span>Already have an account?<a href="LoginHomepage.html">  Home </a></span>
			<button> Get Connected </button>
			</form>
		</div>
	</div>
</body>
</html>