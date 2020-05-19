<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Image Upload</title>
<link rel="stylesheet" type="text/css" href="CSS/login.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
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
</head>

<body>
	<header>
		<nav class="navbar navbar-dark navbar-custom"
			style="background-color: tomato">
			<div>
				<a href="/dashboard" class="navbar-brand"> Home </a>
			</div>
		</nav>
	</header>
	<div class="body">
		<div class="login">
			<h2 class="loginTitle">Image Upload</h2>
			<div id="formStatus"></div>
			<form action="/imageUpload" method="post" enctype="multipart/form-data">
				<div class="text">
					<input type="file" name="image" placeholder="Select a file"
						accept="image/x-png,image/gif,image/jpeg" />
				</div>
				<input type="submit" name="UploadImage" placeholder="Upload Image" />
			</form>
		</div>
	</div>
</body>

</html>