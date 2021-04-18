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
}
.mright{
margin-right:10%;
}
</style>
</head>
<body>
<div class="container m-4 box">
	<hr>
	<jsp:include page="top.jsp"></jsp:include>
	<hr>
    <h1 class="text-primary text-center">Invoice</h1>
	<h6 class="text-center text-success">${successMsg}</h6>
<h6>
Invoice No: ${totalDetails.getInvid()}<br>
Invoice Date: ${totalDetails.getInvoiceDate()}
</h6>
<h6>
Customer Name: ${totalDetails.getCustomerMaster().getCustomerName()}<br>
Customer Email: ${totalDetails.getCustomerMaster().getUser().getEmail()}<br>
Customer Phone: ${totalDetails.getCustomerMaster().getCustomerPhone()}<br>
Customer Address: ${totalDetails.getCustomerMaster().getCustomerAddress()}
</h6>
	<table class="table mt-1">
	<thead class="bg-primary text-white">
	<tr>
	<th>Item ID</th>
	<th>Item Name</th>
	<th>Item Image</th>
	<th>Item Unit</th>
	<th>Item Price</th>
	<th>Item Quantity</th>
	<th>Total Price</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${totalDetails.getItemList()}" var="item">
	<tr>
	<td>${item.key.getItemid()}</td>
	<td>${item.key.getItemName()}</td> 
	<td><img src="${item.key.getImageURL()}" width="100" height="100"></td>
	<td>${item.key.getItemUnit()}</td>
	<td>${item.key.getItemPrice()}</td>
	<td>${item.value}</td>
	<td>${item.key.getItemPrice()*item.value}</td>
	</tr>
	</c:forEach>
	</tbody>
		</table><br>
	<h6 class="text-right mright">Grand Total: ${totalDetails.getTotal()}</h6><br>
	<div class="row">
	<div class="col-sm">
	<a href="/ShoppingCartSpringMVC/invoice/exportpdf/${totalDetails.getInvid()}">
	<button class="btn btn-primary mb-2 btn-block">Export Invoice as PDF</button>
	</a>
	</div>
	<div class="col-sm">
	<a href="/ShoppingCartSpringMVC/invoice/exportexcel/${totalDetails.getInvid()}">
	<button class="btn btn-primary mb-2 btn-block">Export Invoice as Excel</button>
	</a>
	</div>
	</div>
	<div class="row">
	<div class="col-sm">
	<a href="/ShoppingCartSpringMVC/invoice/exportmail/${totalDetails.getInvid()}">
	<button class="btn btn-primary mb-2 btn-block">Send Invoice via Email</button>
	</a>
	</div>
	<div class="col-sm">
	<a href="/ShoppingCartSpringMVC/invoice/exportsms/${totalDetails.getInvid()}">
	<button class="btn btn-primary mb-2 btn-block">Send Invoice via SMS</button>
	</a>
	</div>
	</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>