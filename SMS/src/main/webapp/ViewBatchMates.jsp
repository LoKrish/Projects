<%@page import="com.sms.student.model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>BatchMates</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<style>
.navbar-custom {
	background: radial-gradient(circle, rgba(11, 232, 129, 1) 0%,
		rgba(69, 84, 96, 1) 100%);
}

.body {
	background: radial-gradient(circle, rgba(11, 232, 129, 1) 0%,
		rgba(69, 84, 96, 1) 100%);
}

@font-face {
	font-family: 'DM Sans', sans-serif;
}

* {
	font-family: 'DM Sans', sans-serif;
}

.row {
	background-color: #ecf0f1;
}
</style>
<body>

	<header>
		<nav class="navbar navbar-dark navbar-custom"
			style="background-color: tomato">
			<div>
				<a href="/dashboard" class="navbar-brand"> Home </a>
			</div>
		</nav>
	</header>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">BatchMates</h3>
			<hr>
			<div class="container text-left"></div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>DateOfBirth</th>
					</tr>

				</thead>
				<tbody>
					<c:forEach var="student" items="${batchmates}">
						<tr>
							<td><c:out value="${student.getFirstName()}" /></td>
							<td><c:out value="${student.getLastName()}" /></td>
							<td><c:out value="${student.getEmail()}" /></td>
							<td><c:out value="${student.getDateOfBirth()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>