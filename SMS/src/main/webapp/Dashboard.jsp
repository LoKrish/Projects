<%@page import="com.sms.student.model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>
<link rel="stylesheet" type="text/css" href="CSS/Dashboard.css">
<script type="text/javascript" src="JS/Dashboard.js"></script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0);
	%>
	<div class="grid-container">b
		<div class="menu-icon">
			<i class="fas fa-bars"></i>
		</div>
		<aside class="sidenav">
			<div class="sidenav__close-icon">
				<i class="fas fa-times"></i>
			</div>
			<ul class="sidenav__list">
				<li class="sidenav__list-item"><a href="/dashboard"> Home </a></li>
				<li class="sidenav__list-item"><a href="/viewBatchmates">
						View Batchmates</a></li>
				<li class="sidenav__list-item"><a href="/editUserDetails">
						Edit profile </li>
				<li class="sidenav__list-item"><a href="/uploadProfilePicture">Upload
						Image </a></li>
				<li class="sidenav__list-item"><a href="/logout"> Logout </a></li>
			</ul>
		</aside>
		<main class="main">
			<div class="main-header">
				<div class="content">
					<h1 class="well" style="margin-left:450px;">
						<c:out value="${student.getFirstName()}"></c:out>
					</h1>
					<br /> <br />

					<div>
						<c:if test="${student.getImage()==null}">
							<img class="profile"
								src="data:image/png;base64,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"
								alt="" style="margin-left:405px;" width="200" height="200"/>
						</c:if>
						<c:if test="${student.getImage()!=null}">
							<img class="profile"
								src="<c:out value="${student.getImage()}"></c:out> "  style="margin-left:405px;" width="200" height="250"/>
						</c:if>



						<div class="list-group col-md-6 pull-right pad" style="margin-left:-90px;">
							<p class="list-group-item highlight">
								<span class="pull-right"><c:out
										value="${student.getFirstName()}  ${student.getLastName()}"></c:out></span>
							</p>
							<p class="list-group-item highlight">
								<span class="pull-right"><c:out
										value="${student.getEmail()}"></c:out></span>
							</p>
							<br />
						</div>
					</div>
					<br />
				</div>

			</div>
		</main>
		<footer class="footer">
			<div class="footer__copyright">@SMS_2020</div>
			<div class="footer__signature">Student Management System</div>
		</footer>
	</div>
</body>
</html>