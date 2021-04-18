<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%@ page import="java.util.ResourceBundle"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<title>Shopping</title>
<style>
html{
background: url(https://st.depositphotos.com/2155259/3254/v/600/depositphotos_32546037-stock-illustration-background-with-colorful-shopping-bags.jpg) no-repeat center center fixed;
}
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
<h1>Update Item</h1>
	<spring:form action="/ShoppingCartSpringMVC/admin/submitupdateitem" commandName="itemMaster" method="post" class="form-group">
	<spring:input path="itemid" type="hidden"/>
		<div class="form-group row">
		<label for="itemNameInput" class="col-sm-4 col-form-label">Item Name</label>
		<div class="col-sm-8">
		<spring:input path="itemName" class="form-control" type="text" name="itemName" id="itemNameInput" />
		</div>
		</div>
		<div class="form-group row">
		<label for="itemUnitInput" class="col-sm-4 col-form-label">Item Unit</label>
		<div class="col-sm-8">
		<spring:input path="itemUnit" class="form-control" type="text" name="itemUnit" id="itemUnitInput" />
		</div>
		</div>
		<div class="form-group row">
		<label for="itemPriceInput" class="col-sm-4 col-form-label">Item Price</label>
		<div class="col-sm-8">
		<spring:input path="itemPrice" class="form-control" type="text" name="itemPrice" id="itemPriceInput" />
		</div>
		</div>
		<div class="form-group row">
		<label for="itemCategoryInput" class="col-sm-4 col-form-label">Item Category</label>
		<div class="col-sm-8">
		<spring:input path="itemCategory" class="form-control" type="text" name="itemCategory" id="itemCategoryInput" />
		</div>
		</div>
		<div class="form-group row">
		<label for="imageURLInput" class="col-sm-4 col-form-label">Image URL</label>
		<div class="col-sm-8">
		<spring:input path="imageURL" class="form-control" type="text" name="imageURL" id="imageURLInput" />
		</div>
		</div>
		<div class="form-group row">
		<label for="itemStockInput" class="col-sm-4 col-form-label">Item Stock</label>
		<div class="col-sm-3">
		<spring:input path="itemStock" class="form-control" type="text" name="itemStock" id="itemStockInput" />
		</div>
		<div class="col-sm-1">
		<button class="btn btn-dark btn-block" onclick="return addstock(1)">+1</button>
		</div>
		<div class="col-sm-1">
		<button class="btn btn-dark btn-block" onclick="return addstock(5)">+5</button>
		</div>
		<div class="col-sm-1">
		<button class="btn btn-dark btn-block" onclick="return addstock(10)">+10</button>
		</div>
		<div class="col-sm-1">
		<button class="btn btn-dark btn-block" onclick="return addstock(20)">+20</button>
		</div>
		<div class="col-sm-1">
		<button class="btn btn-dark btn-block" onclick="return addstock(50)">+50</button>
		</div>
		</div>
		<input type="submit" value="Submit" class="btn btn-primary mb-1">
		<script>
		function addstock(value){
			stock = document.getElementById("itemStockInput");
			stock.value= Number(stock.value)+value;
			return false;
		}
		</script>
	</spring:form>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>