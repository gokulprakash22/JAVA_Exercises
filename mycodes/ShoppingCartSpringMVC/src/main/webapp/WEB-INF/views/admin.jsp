<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<title>Shopping</title>
<style>
.box {
border:1px solid black;
text-align:center;
}
.action {
width:100%;
}
</style>
</head>
<body>
<div class="container m-4 box">
	<hr>
	<jsp:include page="top.jsp"></jsp:include>
	<hr>
   <h1 class="text-info">Items</h1>
	<table class="table">
	<thead class="bg-info text-white">
	<tr>
	<th>Item ID</th>
	<th>Item Name</th>
	<th>Image</th>
	<th>Item Unit</th>
	<th>Item Price</th>
	<th>Item Category</th>
	<th>Item Stock</th>
	<th>Actions</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${itemMasterList}" var="item">
	<tr>
	<td><span>${item.getItemid()}</span></td>
	<td><span>${item.getItemName()}</span></td>
	<td><img src=<c:out value='${item.getImageURL()}'/> width="100" height="100"></td>
	<td><span>${item.getItemUnit()}</span></td>
	<td><span>${item.getItemPrice()}</span></td>
	<td><span>${item.getItemCategory()}</span></td>
	<td><span>${item.getItemStock()}</span></td>
	<td><span><a href="/ShoppingCartSpringMVC/admin/updateitem/${item.getItemid()}"><button class="btn btn-info mb-1 action">Update</button></a><br>
	<a href="/ShoppingCartSpringMVC/admin/deleteitem/${item.getItemid()}"><button class="btn btn-danger action">Delete</button></a></span></td>
	</tr>
</c:forEach>
</tbody>
		</table>
		<a href="/ShoppingCartSpringMVC/admin/additem"><button class="btn btn-info mb-2 btn-block">Add Item</button></a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>