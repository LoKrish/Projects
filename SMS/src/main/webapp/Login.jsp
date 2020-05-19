<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="CSS/login.css">
	<link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&display=swap" rel="stylesheet">
	<script type="text/javascript" src="JS/Login.js"></script>
</head>

<body>
	<div class="body">
		<div class="login">
			<h2 class="loginTitle"> Login </h2>
			<div id="formStatus"></div>
			<form action="#" onsubmit="login();return false" method="POST">
				<div class="email">
					<input type="email" name="email" placeholder="email" id="email" onblur="validateEmail()" />
				</div>
				<div class="password">
					<input type="password" name="password" placeholder="password" id="password"
						onblur="validatePassword()">
				</div>
				<button id="loginButton"> Login </button>
			</form>
		</div>
	</div>
</body>

</html>