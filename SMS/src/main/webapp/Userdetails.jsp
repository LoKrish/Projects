<%@page import="com.sms.student.model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Sign UP</title>
	<link rel="stylesheet" type="text/css" href="CSS/SignUp.css">
	<link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&display=swap" rel="stylesheet">
</head>

<body>
	<div class="body">
		<div class="login">
			<h2 class="loginTitle"> User Details </h2>
			<div id="formStatus"></div>
			<form id="SignupForm" action="#" onsubmit="formSubmit();return false">
				<div id="firstNameStatus"></div>
				<div class="text">
					<input type="text" name="firstName" id="firstName"
						value='<c:out value="${student.getFirstName()}"></c:out>' onblur="validateFirstName" />
				</div>
				<div id="lastNameStatus"></div>
				<div class="text">
					<input type="text" name="lastName" id="lastName"
						value='<c:out value="${student.getLastName()} "></c:out>' onblur="validateLastName()" />
				</div>
				<div class="text">
					<input type="date" name="dateOfBirth" id="dateOfBirth"
						value="" />
				</div>
				<div id="emailStatus"></div>
				<div class="text">
					<input type="email" name="email" id="email"
						value='<c:out value="${student.getEmail()}"></c:out>' onblur="validateEmail()"/>
				</div>
				<span>Return to home<a href="/dashboard"> Home </a></span>
				<button id="submitBtn"> Update </button>
			</form>
		</div>
	</div>
</body>

</html>