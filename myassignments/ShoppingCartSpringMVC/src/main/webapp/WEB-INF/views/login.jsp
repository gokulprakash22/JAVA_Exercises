<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="mytags" %>
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
h6{
color:${loginMsgColor};
}
</style>
</head>
<body>
<div class="container m-4 box">
<h1>Login page</h1>
<h6>${loginMsg}</h6>
	<spring:form action="/ShoppingCartSpringMVC/submitlogin" commandName="user" method="post" class="form-group">
	<div class="form-group row">
		<label for="emailinput" class="col-sm-4 col-form-label"><mytags:bundle key="email"/></label>
		<div class="col-sm-8">
		<spring:input path="email" class="form-control" type="email" name="email" id="emailinput" />
		</div>
	</div>
	<div class="form-group row">
		<label for="passwordinput" class="col-sm-4 col-form-label"><mytags:bundle key="password"/></label>
		<div class="col-sm-8">
		<spring:input path="pass" class="form-control" type="password" name="pass" id="passwordinput" />
		</div>
	</div>
		<input type="submit" value="Login" class="btn btn-primary mb-1">
	</spring:form>
	New user? <a href="/ShoppingCartSpringMVC/register">Click here to register.</a><br>
	Admin? <a href="/ShoppingCartSpringMVC/admin">Click here to login.</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</body>
</html>