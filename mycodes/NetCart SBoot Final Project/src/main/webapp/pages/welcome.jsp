<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>NetCart Home</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>

</style>
<body>

	<nav class="navbar navbar-expand-sm bg-primary navbar-primary">
  <ul class="navbar-nav ml-auto">
   <li class="nav-item pl-2">
      <a class="nav-link" href="profile"><span class="text-white">PROFILE</span></a>
    </li>
    <li class="nav-item pl-2">
      <a class="nav-link" href="/logout"><span class="text-white">LOGOUT</span></a>
    </li>
    
  </ul>
</nav>

	 <div class="container h-100 p-5">
	 
		<div class="row">
		<div class="col-6">
		<img class="img-fluid" width="700" height="700" src="https://cdn.dribbble.com/users/2279668/screenshots/9011709/online-shopping-vector-illustration1.webp" />
		</div>
		<div class="col-6 mt-5">
			<header class="text-white py-5 mb-5">
				<h1 class="font-weight-bold text-dark pb-2">Welcome ${customer.customername} </h1>
				<a href="Stationary"><h3 class="text-dark"><input type="submit" value="Cilck here to Start Shopping" class="btn btn-lg btn-outline-primary"></h3></a>
			</header>
			</div>
		
		</div>
	  </div>
	
	
	
</body>
</html>