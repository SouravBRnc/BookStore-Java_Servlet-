<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p align="right"><a href="BookStoreServlet?params=logout">Logout</a></p>
<div class="menu-bar" style="color:'powderblue';">
	<table style="width:100%" bgcolor="powderblue">
		<tr>
			<th style="border:1px solid black"><a href="BookStoreServlet?params=home">Home</a></th>
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
<div class="form-group" align="center"><h2><u>Buy Books Portal</u></h2></div>
<form action="BookStoreServlet" method="post">
	<div class="form-group">
    <label for="email">Enter the customer's registered email (Also enter for new Customer)</label>
    <input type="email" class="form-control" name="email" placeholder="e-mail">
  </div>
  <div class="form-group">
    <label for="name">Enter the name of the book</label>
    <input type="text" class="form-control" name="name" placeholder="Book-Name">
  </div>
  <button type="submit" name="chk_cust_btn" class="btn btn-primary">Search Customer</button>
</form>
</body>
</html>