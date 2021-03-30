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
<h1>Welcome <%=session.getAttribute("name") %>!! Lets Do Shopping</h1>
	<form action="shopping.do" method="post">
		<input type="hidden" name="formid" value="shopping">
		<input type="hidden" name="shopid" value="biscuits">
		<input type="submit" value="Go to shopping" class="btn btn-primary mb-4">
	</form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>