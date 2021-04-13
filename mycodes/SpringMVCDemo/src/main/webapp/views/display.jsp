<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome page -display page...</h1>
	<%=request.getAttribute("itemlist") %>
	<c:forEach  items="${itemlist}" var="item">
		<c:out value="${item}"/>
	</c:forEach>
</body>
</html>