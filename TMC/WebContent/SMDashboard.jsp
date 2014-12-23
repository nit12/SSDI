<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.services.*"%>
    <%@ page import="com.entities.*"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Welcome to TMS</title>
</head>

<body>
<div id="container">
		 <%@include file="header.jsp" %>
       
         <%@include file="site_navigation.jsp" %>
        
        <%@include file="leftpane_navigation.jsp" %>
        
        
        
        
		<div id="content">
        
        
        <div id="content_top"></div>
        <div id="content_main">
        	<h2><u>Enrolled Staff</u></h2>
<p>&nbsp;</p>

			
           	
           	
           	<%
           	wbservice wb=new wbservice();
           	User user=(User)session.getAttribute("User");
           // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           	List<Tournament> tour=wb.getTournaments();
           	List<Transaction> trans=wb.getTransactions();
           	List<Staff> staff=wb.getStaff();
           	if(null!=staff && staff.size()!=0){%>
           	<table border="0" bordercolor="#000033" width="100%">
           	<tr><td><h3>Staff ID</h3></td>
           	<td><h3>Name</h3></td>
           	<td><h3>Contact No</h3></td>
           	<td><h3>Role</h3></td>
           	<td><h3>Team</h3></td>
           	</tr>
           	<%
           	for(Staff s: staff){
           		if(s.getTid().hashCode()==(user.getTid()).hashCode()){
           	 %>
           	 
           	<tr><td><%=s.getStaffId()%></td>
           	<td><%=s.getStaffName()%></td>
           	<td><%=(Integer)s.getContactNo() %></td>
           	<td><%=s.getRoleId().getRoleName()%></td>
           	<td>
           	          	
           	<%
           	if(s.getTeamId()!=null)
           	{
           	out.println(s.getTeamId().getTeamName());
           	}
           	else
           	{
           	out.println("Not Allocated");
           	}
           	
           	%></td>
           	</tr>
           	
           	<%}}%>
           	</table>
           	<%}
           	else
           	out.println("Currently there are no staff allocated");
           	 %>
           	
           	
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	
       	  <!-- <h3>Template Notes</h3>
       	  <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        	<p>&nbsp;</p>
		  <h3>More information</h3>
        	<p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
       	  <p>&nbsp;</p>
        	<h3>Template Notes</h3>
            <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
          <p></p> -->
<p>&nbsp;</p>
        </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>


</body>
</html>