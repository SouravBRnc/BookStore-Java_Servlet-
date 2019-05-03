<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
<div class="form-group" align="center"><h2><u>Add Books Portal</u></h2></div>
<form action="BookStoreServlet" method="post">
  <div class="form-group">
    <label>Book-Name</label> 
    <input type="text" class="form-control" name="bookname" placeholder="book-name">
  </div>
  <div class="form-group">
    <label>Author</label> 
    <input type="text" class="form-control" name="author" placeholder="author">
  </div>
  <div class="form-group">
    <label>Select genre</label>
    <select class="form-control" name="genre">
      <option value="fiction">Fiction</option>
	  <option value="thriller">Thriller</option>
	  <option value="horror">Horror</option>
	  <option value="mystery">Mystery</option>
      <option value="comic">Comic</option>
	  <option value="autobiography">Autobiography</option>
	  <option value="biography">Biography</option>
	  <option value="academic">Academic</option>
    </select>
  </div>
  <div class="form-group">
    <label>Price</label> 
    <input type="text" class="form-control" name="price" placeholder="price">
  </div>
  <div class="form-group">
    <label>Numbers(Quantity)</label> 
    <input type="text" class="form-control" name="qty" placeholder="how many books?">
  </div>
  <button type="submit" name="add_book_btn" class="btn btn-primary">Add Book</button>
</form>
</body>
</html>