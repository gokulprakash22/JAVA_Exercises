<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.invoice_db.ItemMasterDTO" %>
    <%@page import="java.util.Enumeration"%>
    <%@ page import="java.util.Set" %>
    <%@ page import="java.util.HashSet" %>
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
    <h1 class="text-primary">Invoice</h1>

Customer Name : ${totaldetails.getCustomermasterdto().getCustomername()}<br>
Customer Email: ${totaldetails.getCustomermasterdto().getCustomeremail()}<br>
Customer Phone: ${totaldetails.getCustomermasterdto().getCustomerphone()}<br>
Customer Address: ${totaldetails.getCustomermasterdto().getCustomeraddress()}<br>
	<table class="table mt-1">
	<thead class="bg-primary text-white">
	<tr>
	<th>Item Name</th>
	<th>Item Quantity</th>
	<th>Item Price</th>
	<th>Image</th>
	<th>Select</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${totaldetails.getItemdetails()}" var="item">
	<tr>
	<td>${item.getItemno()}</td> 
	<td>${item.getItemdescription()}</td>
	<td>${item.getItemunit()}</td>
	<td>${item.getItemprice()}</td>
	<td>${item.getItemunit()}</td>
	</tr>
	</c:forEach>
	</tbody>
		</table>
	<div class="row">
	<div class="col-sm">
	<form action="pdf.do" method="post">
	<input type="hidden" name="formid" value="pdf">
	<input type="submit" value="Export Invoice as PDF" class="btn btn-primary mb-2 btn-block">
	</form>
	</div>
	<div class="col-sm">
	<form action="excel.do" method="post">
	<input type="hidden" name="formid" value="excel">
	<input type="submit" value="Export Invoice as Excel" class="btn btn-primary mb-2 btn-block">
	</form>
	</div>
	</div>
	<div class="row">
	<div class="col-sm">
	<form action="email.do" method="post">
	<input type="hidden" name="formid" value="email">
	<input type="submit" value="Send Invoice via Email" class="btn btn-primary mb-2 btn-block">
	</form>
	</div>
	<div class="col-sm">
	<form action="sms.do" method="post">
	<input type="hidden" name="formid" value="sms">
	<input type="submit" value="Send Invoice via SMS" class="btn btn-primary mb-2 btn-block">
	</form>
	</div>
	</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>