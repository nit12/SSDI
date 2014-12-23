<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body>
<div id="container">
		 <%@include file="header.jsp" %>
<% session.invalidate(); %>
<p>You have been successfully logout</p>
<%

response.sendRedirect("login.jsp");

 %>
<%@include file="footer.jsp" %>
   </div>
  
</body>
</html>