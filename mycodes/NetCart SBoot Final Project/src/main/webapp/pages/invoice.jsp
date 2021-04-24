
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NetCart Invoice</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<style>
body{
	font-size : 14px;
	background-color: #f2f6f9;
}

.minicart{
	background:url(cart.png);
	background-position: -156px 11px;
	background-repeat:no-repeat;
	width: 40px;
	height: 50px;
	padding-right: 32px;
}
container-fluid.text-center{
	padding: 0 32px;
	
	background-color: #ffffff;
}

.row{
	box-shadow: 0 1px 0 #e1e5e8;
	padding-bottom :0;
	padding:10px;
	margin-bottom: 11px;
}

.row1{
	box-shadow: 0 1px 0 #e1e5e8;
	padding-bottom :0;
	margin-bottom: 11px;
}
.columnCaptions{
	color: #7e93a7;
	font-size:12px;
	padding: 0;
	box-shadow: 0 0 0;
	background-color: #f2f6f9;
}
.itemName{	
	color: #727578;
	font-size :16px;
	font-weight: bold; 
	float: left;
	padding-left:25px;
}
.quantity{	
	color: #4ea6bc;
	font-size :18px;
	font-weight: bold;
	float : left;
	width: 42px;
	padding-left: 7px;
}
.price{
	color: #f06953;
	font-size :18px;
	font-weight: bold;
	float: right;
}
.card {
    box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    border-radius: 1rem;
    border: transparent
}
body {
  background: #eecda3;
  background: linear-gradient(to right, #00c6ff, #0072ff);
  min-height: 100vh;
}
.navbar{
 background: linear-gradient(to right, #00c6ff, #0072ff) !important;
} 
.btn-primary{
background:linear-gradient(to right, #00c6ff, #0072ff) !important;
} 
</style>
</head>

<body>
<nav class="navbar navbar-expand-sm bg-primary navbar-primary">
  <ul class="navbar-nav ml-auto">
    <li class="nav-item pl-2">
      <a class="nav-link" href="/logout"><span class="text-white">LOGOUT</span></a>
    </li>
    
  </ul>
</nav>
<div class="container-fluid text-center p-5">
            <div class="card">
<p class="display-3 font-weight-bold">Invoice</p>

            <div class="row">
			<div class="col-6">
				
				<h1 class="font-weight-light text-center">Your Details</h1>
	<h4 class="font-weight-light">Customer Name : ${invoicewrapperdetails.getCustomer().getCustomername()}</h4>
	<h4 class="font-weight-light">Customer Email: ${invoicewrapperdetails.getCustomer().getCustomeremail()}</h4>
	<h4 class="font-weight-light">Customer Phone: ${invoicewrapperdetails.getCustomer().getCustomerphone()}</h4>
	<h4 class="font-weight-light">Customer Address: ${invoicewrapperdetails.getCustomer().getCustomeraddress()}</h4>
	</div>
	<div class="col-6">

	<table width="500px;">
		<tr><h1 class="font-weight-light text-center" style="width:500px;">Items Purchased</h1></tr>

		<tr class="row list-inline columnCaptions">
        <th class="col-2">Item No</th>
        <th class="col-2">Item Name</th>
        <th class="col-3">Item Price</th>
        <th class="col-2">Item Qty</th>
        <th class="col-3">Total</th>
		</tr>
	<c:forEach items="${invoicewrapperdetails.getInvoice().getItemquantitymap()}" var="item">
	<tr class="row">
	<td class="col-2 quantity">${item.key.getItemno()}</td> 
	<td class="col-2 itemname">${item.key.getItemdescription()}</td>
	<td class="col-3 price">&#8377;${item.key.getItemprice()}</td>
	<td class="col-2 quantity">${item.value}</td>
	<td class="col-3 price">&#8377;${item.value*item.key.getItemprice()}</td>
	</tr>
	 <c:set var="data" value="${data+item.value*item.key.getItemprice()}" />
	</c:forEach>

	<c:set var="tax" value="${(0.05)*data}" />
	<c:set var="shipping" value="200" />
	
	
	<tr class="row">
	<td class="col-2"></td><td class="col-2"></td><td class="col-2"></td>
	<td class="col-3"><p class="font-weight-bolder">Subtotal</td>
	<td class="col-3 price">&#8377;<c:out value="${data}"/></td>
	</tr>
	<tr class="row">
	<td class="col-2"></td><td class="col-2"></td><td class="col-2"></td>
	<td class="col-3"><p class="font-weight-bolder">Shipping</td>
	<td class="col-3 price">&#8377;<c:out value="${shipping}"/></td>
	</tr>
	<tr class="row">
	<td class="col-2"></td><td class="col-2"></td><td class="col-2"></td>
	<td class="col-3"><p class="font-weight-bolder">Tax</td>
	<td class="col-3 price">&#8377;<c:out value="${tax}"/></td>
	</tr>
	<tr class="row">
	<td class="col-2"></td><td class="col-2"></td><td class="col-2"></td>
	<td class="col-3"><p class="font-weight-bolder">Total</td>
	<td class="col-3 price">&#8377;<c:out value="${data+tax+shipping}"/></td>
	</tr>
	</table>
		</div>
		</div>
	<div class="row justify-content-center">
	<form action="/export/createPDF" method="POST">
	<div class="col-auto"><input class="btn btn-primary rounded-pill" type="submit" value="Create PDF"></div></form>
	<form action="/export/createExcel" method="post">
	<div class="col-auto"><input class="btn btn btn-primary rounded-pill" type="submit" value="Create Excel"></div></form>
	<form action="/export/sendEmail" method="post">
	<div class="col-auto"><input class="btn btn btn-primary rounded-pill" type="submit" value="Send Email"></div></form>
	<form action="/export/sendSMS" method="post">
	<div class="col-auto"><input class="btn btn btn-primary rounded-pill" type="submit" value="Send SMS"></div></form></div>
</div>
</div>
</body>
</html>