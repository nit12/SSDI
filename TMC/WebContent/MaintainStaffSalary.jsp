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
        	<h2><u>Maintenance of Staff salary</u></h2>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>			
           	
           	
           	<%
           	wbservice wb=new wbservice();
           	User user=(User)session.getAttribute("User");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            out.println("Ongoing Tournament "+user.getTid().getTname());
           //	List<Tournament> tour=wb.getTournaments();
           //	List<Transaction> trans=wb.getTransactions();
           	List<Salary> slist=user.getTid().getSalaryList();%>
           	
           	
				<%
           	if(slist!=null && slist.size()!=0)
           	{%>
           	<form action="/TMC/TMCServlet" method="get" name="updateSalary">
				<input type=hidden name="action" value="updateSalary"/>
           	<table border="0" bordercolor="#000033" width="100%">
           	<tr><td><h3>Staff Role ID</h3></td>
           	<td><h3>Name</h3></td>
           	<td><h3>Salary</h3></td>
           	</tr>
           	<%for(int j=0;j<slist.size();j++){ 
           	Salary s=slist.get(j);
           	
           	
           	%>
           		
           	<tr><td><%=s.getRoleId()%></td>
           	<td><%=s.getRoleName()%></td>
           	<td><input name="salary<%=j%>" type="text" value="<%=s.getSalary()%>"/></td>
           	
           	</tr>
           	
           	<%}%>
           	<tr><td>&nbsp;</td><td><input size="5" type="submit" type="submit" value="Update Salary" /></td></tr>
           	</table></form>
           	<%
           	
           	}
           	else
           	out.println("Currently there are no staff roles allocated");
           	 %>
           	
           	
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<h3><u>Generate salary to staff</u></h3>
           	<p>&nbsp;</p>
           	<%String sMessage=(String)request.getAttribute("SalaryGeneration");
           	if(null!=sMessage)
           	out.println(sMessage);           	
           	 %>
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	Please select the date for generating the salaries to the staff in the tournament.
           	 <form action="/TMC/TMCServlet" method="get" name="generateSalary">
				<input type=hidden name="action" value="generateSalary"/>
           	<table border="0" bordercolor="#000033" width="50%">
           	<tr><td>Date:</td><td><input type="date" name="saldate"/></td></tr>
           	<tr><td>&nbsp;</td><td><input size="5" type="submit" type="submit" value="Generate Salary" /></td></tr>
           	</table></form>
           				
<p>&nbsp;</p>
        </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>


</body>
</html>