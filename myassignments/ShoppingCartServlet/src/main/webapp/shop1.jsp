<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.invoice_db.ItemMasterDTO" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
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
	<form action="shopping.do" method="post">
	<input type="hidden" name="formid" value="shopping">
	<input type="hidden" name="shopid" value="vegetables">
	<input type="hidden" name="currentid" value="biscuits">
   <h1 class="text-info">Biscuits Shop</h1>
	<table class="table">
	<thead class="bg-info text-white">
	<tr>
	<th>Item Name</th>
	<th>Item Quantity</th>
	<th>Item Price</th>
	<th>Image</th>
	<th>Select</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${items}" var="item">
	<tr>
	<td><span>${item.getItemdescription()}</span></td> 
	<td><select class="form-control" name=<c:out value='${item.getItemno()}' />>
	<option value="0">Select quantity</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	</select></td>
	<td><span>${item.getItemprice()}</span></td>
	<td><img src=<c:out value='${item.getItemimage()}'/> width="100" height="100"></td>
	<td><input type="checkbox" name="selected" value=<c:out value='${item.getItemno()}' />></td>
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