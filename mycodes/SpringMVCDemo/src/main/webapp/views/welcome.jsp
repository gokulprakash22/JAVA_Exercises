<%@page import="controlpack.LoginFormBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Spring....Mvc..................</h1>
	<h1>
	<%
		LoginFormBean lfb=(LoginFormBean)request.getAttribute("lfb");
		out.println(lfb.getUname()+":"+lfb.getUpass());
	%>
	</h1>
</body>
</html>