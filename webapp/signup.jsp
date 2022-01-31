<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign Up | Mallet</title>
	</head>
	
	<body>
		<form action="Account" method="POST">
			<input type="hidden" name="form-action" value="register">
		
			<input type="text" name="username" maxlength="120" required>
			<input type="email" name="email" maxlength="260" required>
			<input type="password" name="password" maxlength="500" required>
			<input type="password" name="sec-password" maxlength="500" required>
			
			<button>Sign Up</button>
		</form>
	</body>
</html>