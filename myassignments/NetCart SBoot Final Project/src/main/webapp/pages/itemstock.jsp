<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NetCart Item Stock</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
</head>
<style>
td,th {
  text-align: center;
}
</style>
<body>

<div class="container">
<div class="text-right p-5"><a href="managementhome"><button class="btn btn-dark">Home</button></a></div> 
<p class="display-4 text-center">Item Stock</p>
  <div class="h-100 row align-items-center text-center">
		<div class="col-12">
				<h1 style="padding:10px;">Your Details</h1>
	<h2>Item No : ${item.itemno}</h2>
	<h2>Item Name : ${item.itemdescription}</h2>
	<h2>Item Category : ${item.category}</h2>
	<h2>Item Stock : ${item.stock}</h2>
	</div>
</div>
</div>

</body>
</html>