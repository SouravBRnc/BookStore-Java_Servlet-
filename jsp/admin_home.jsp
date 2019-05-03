<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>SS_BookStore_Admin_HomePage</title>
</head>
<body>
<p align="right"><a href="BookStoreServlet?params=logout">Logout</a></p>
<div class="menu-bar" style="color:'powderblue';">
	<table style="width:100%" bgcolor="powderblue">
		<tr>
			<th style="border:1px solid black"><a href="BookStoreServlet?params=add_book">Add new books</a></th>
			<th style="border:1px solid black"><a href="BookStoreServlet?params=rent_book">Issue/Rent book(s)</a></th>
			<th style="border:1px solid black"><a href="BookStoreServlet?params=new_transact">Process new transaction</a></th>
			<th style="border:1px solid black"><a href="BookStoreServlet?params=process_return">Process return</a></th>
		</tr>
	</table>
</div>
<br>
<hr>
<br>
</body>
</html>