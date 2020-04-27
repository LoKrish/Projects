<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="login.css">
	<link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<div class="body">
		<div class="login">
			<h2 class="loginTitle"> Login </h2>
			<form action="/LoginUser" method="POST">
			<div class="registerNumber">
				<input type="text" name="registerNumber" placeholder="registerNumber"/>
			</div>
			<div class="password">
				<input type="password" name="password" placeholder="password">
			</div>
			<button> Login </button>
			</form>
		</div>
	</div>
</body>
</html>