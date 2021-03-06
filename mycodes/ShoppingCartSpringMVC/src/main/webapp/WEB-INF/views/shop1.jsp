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
</style>
</head>
<body>
<div class="container m-4 box">
	<hr>
	<jsp:include page="top.jsp"></jsp:include>
	<hr>
	<form action="/ShoppingCartSpringMVC/shopping/shop2" method="post">
   <h1 class="text-info">Biscuits Shop</h1>
	<table class="table">
	<thead class="bg-info text-white">
	<tr>
	<th>Item ID</th>
	<th>Item Name</th>
	<th>Item Image</th>
	<th>Item Unit</th>
	<th>Available Stock</th>
	<th>Item Quantity</th>
	<th>Item Price</th>
	<th>Select</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${itemMasterList}" var="item">
	<tr>
	<td><span>${item.getItemid()}</span></td>
	<td><span>${item.getItemName()}</span></td>
	<td><img src="${item.getImageURL()}" width="100" height="100"></td>
	<td><span>${item.getItemUnit()}</span></td>
	<td><span>${item.getItemStock()}</span></td> 
	<c:if test="${item.getItemStock()==0}">
	<td><span class="text-danger">Item Unavailable</span></td>
	</c:if>
	<c:if test="${item.getItemStock()!=0}">
	<td><input type="number" min=0 max="${item.getItemStock()}" name="${item.getItemid()}"></td>
	</c:if>
	<td><span>${item.getItemPrice()}</span></td>
	<td><input type="checkbox" name="selected" value="${item.getItemid()}" <c:if test="${item.getItemStock()==0}">disabled="true"</c:if>></td>
	</tr>
</c:forEach>
</tbody>
		</table>
		<input type="submit" value="Next Shop" class="btn btn-info mb-2 btn-block">
	</form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>