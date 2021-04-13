<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<spring:form action="submitcheckform" method="post" commandName="list">
		<spring:checkboxes items="${modelItemList}" path="itemlist"/>
		
		<input type="submit" value="Next...">
	</spring:form>
</body>
</html>