<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>New Product - Mallet</title>
	</head>
	
	<body>
		<form action="/Mallet/Shop" method="POST">
			<input type="hidden" name="form-action" value="new-product"/>
			
			<input type="text" name="name" required/>
			<input type="text" name="description" required/>
			<select name="category">
				<option value="books">Books</option>
				<option value="music-disks">Music Disks</option>
				<option value="unknown" selected>No Category</option>
			</select>
			<input type="number" name="price" required/>
			
			<button>Send</button>
		</form>
	</body>
</html>