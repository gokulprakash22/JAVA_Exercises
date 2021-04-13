<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<spring:form action="submitform" method="post" commandName="lfb">
	UserName:<spring:input path="uname"/>
	PassWord:<spring:input path="upass"/>
	
	<input type="submit" value="login">
</spring:form>
</body>
</html>