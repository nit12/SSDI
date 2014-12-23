<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Add Sponsor</title>
</head>
<body>
<div id="container">
		 <%@include file="header.jsp" %>
       
         <%@include file="site_navigation.jsp" %>
        
        <%@include file="leftpane_navigation.jsp" %>
        
        
        
        
		<div id="content">
        
        
        <div id="content_top"></div>
        <div id="content_main">
				<form action="/TMC/TMCServlet" method="get" name="newTour">
				<input type=hidden name="action" value="addExpenditure">
				<table width="559" height="20" border="0">
				  <tr>
				    <td>Reason</td>
				    <td><input name="spname" type="text" /></td>
				  </tr>
				  <tr>
				    <td>Amount Spent</td>
				    <td><input name="spamount" type="text" /></td></tr>
				  <tr>
				    <td>Date </td>
				    <td><input type="date" name="spdate"/></td>
				  </tr>
				  <tr>
				  <td></td><td><input size="5" type="submit" type="submit" value="Add Expenditure" /></td>
				  </tr></table>				
				</form>


 </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>

</body>
</html>