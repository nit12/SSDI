<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Create Staff</title>
</head>
<body>
<div id="container">
		 <%@include file="header.jsp" %>
       
         <%@include file="site_navigation.jsp" %>
        
        <%@include file="leftpane_navigation.jsp" %>
        
        
        
        
		<div id="content">
        
        
        <div id="content_top"></div>
        <div id="content_main">
        <h2><u>Create new staff</u></h2>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
				<form action="/TMC/TMCServlet" method="get" name="createStaff">
				<input type=hidden name="action" value="createStaff">
				<table width="100%" height="20" border="0">
				  <tr>
				    <td>Staff Name</td>
				    <td><input name="staffname" type="text" /></td>
				  </tr>
				  <tr>
				    <td>Staff Contact No</td>
				    <td><input name="staffcontact" type="text" /></td></tr>
				  <tr>
				    <td>Role Name </td>
				    <td>
				    <%
				    User user=(User)session.getAttribute("User");
				    List<Salary> roles=user.getTid().getSalaryList();
				    
				     %>
				    <select name="roleId">
				    <%for(Salary r:roles){ %>
				    <option value="<%=r.getRoleId()%>"><%=r.getRoleName() %></option>
				     <%} %>
				    </select>
				    </td>
				  </tr>
				  <tr>
				  <td></td><td><input size="5" type="submit" type="submit" value="Add Sponsor" /></td>
				  </tr></table>				
				</form>


 </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div></div>

</body>
</html>