<%@page import="com.sms.student.model.Student"%> <%@page
import="java.util.ArrayList"%> <%@ page language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
  <title>Sign UP</title>
  <link rel="stylesheet" type="text/css" href="CSS/SignUp.css" />
  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&display=swap" rel="stylesheet" />
  <script type="text/javascript" src="JS/SignUp.js"></script>
</head>

<body>
  <script type="text/javascript" src="JS/SignUp.js"></script>
  <div class="body">
    <div class="login">
      <h2 class="loginTitle">Sign UP</h2>
      <div id="formStatus"></div>
      <form id="SignupForm" method="post" action="#" onsubmit="formSubmit();return false">
        <div id="firstNameStatus"></div>
        <div class="text">
          <input type="text" name="firstName" placeholder="First Name" id="firstName" onblur="validateFirstName()"
            required />
        </div>
        <div id="lastNameStatus"></div>
        <div class="text">
          <input type="text" name="lastName" placeholder="Last Name" id="lastName" onblur="validateLastName()"
            required />
        </div>
        <div id="departmentStatus"></div>
        <div class="text">
          <input list="department" name="department" placeholder="Department" id="department" required />
          <datalist id="department">
            <option value="CSE"></option>
            <option value="MECH"></option>
            <option value="CIVIL"></option>
            <option value="ECE"></option>
            <option value="EEE"></option>
          </datalist>
        </div>
        <div id="printStatus"></div>
        <div class="text">
          <input type="text" name="email" placeholder="Email" id="email" onblur="validateEmail()" required />
          <br />
        </div>
        <div id="passwordStatus"></div>
        <div class="text">
          <input type="password" name="password" placeholder="Password" id="password" onblur="validatePassword()"
            required />
        </div>
        <span>Already have an account?<a href="Login.html"> Login </a></span>
        <button id="submitBtn">
          Get Started
        </button>
      </form>
    </div>
  </div>
</body>

</html>