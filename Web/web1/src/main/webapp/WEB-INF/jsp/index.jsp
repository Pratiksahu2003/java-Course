<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Index</title>
	<link rel="stylesheet" href="/style.css">
</head>
<body>
	<form action="/" method="post">
		<input type="text" name="username" placeholder="Enter your username">
		<input type="password" name="password" placeholder="Enter your password">
		<input type="submit" value="Login">
	</form>
	<p>${message}</p>
</body>
</html>
