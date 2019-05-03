<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>SS_BookStore_Admin_Login</title>
</head>
<body>
<br>
<marquee><h2>Welcome to SS BookStore Admin Portal</h2></marquee>
<br>
<hr>
<h3 align="center">Please fill in your user-name and password below</h3>
<form action="BookStoreServlet" method="post">
  <div class="form-group">
    <label for="name">Username:</label>
    <input type="text" class="form-control" name="name" id="name" placeholder="user-name">
  </div>
  <div class="form-group">
    <label for="pass">Password:</label>
    <input type="password" class="form-control" name="pass" id="pass" placeholder="password">
  </div>
  <button type="submit" name="admin_login_btn" class="btn btn-primary">Login</button>
</form>
</body>
</html>