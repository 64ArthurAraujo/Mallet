<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign In - Mallet</title>
	</head>
	<body>
		<form action="Account" method="POST">
			<input type="hidden" name="form-action" value="login">
			
			<input type="text" name="username" maxlength="120" required>
			<input type="password" name="password" maxlength="500" required>
			
			<button>Sign In</button>
		</form>
	</body>
</html>