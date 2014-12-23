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
        	<h2><u>Sponsors for Tournament</u></h2>
<p>&nbsp;</p>
           	<table border="0" bordercolor="#000033" width="100%">
           	<tr><td><h3>Sponsor</h3></td>
           	<td><h3>Amount</h3></td>
           	<td><h3>Date</h3></td>
           	</tr>
           	           	
           	<%
           	wbservice wb=new wbservice();
           	User user=(User)session.getAttribute("User");
           	
           	List<Transaction> trans=wb.getTransactions();
           	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           	for(Transaction t: trans){
           		if(t.getTid().hashCode()==user.getTid().hashCode()){
           		if(t.getTransType().equalsIgnoreCase("CR")){	
           		
           	
           	 %>
           	<tr><td><%=t.getReason()%></td>
           	<td><%=t.getAmount()%></td>
           	<td><%=format.format(t.getTransDate())%></td>
           	</tr>
           	 
           	<%}}}
           	%>
           	
           	</table>
           	<p>&nbsp;</p><h3>OverAll Budget: <%
           	double sum=0;
           	for(Transaction t1: trans){
           		if(t1.getTid().hashCode()==user.getTid().hashCode()){
           			if(t1.getTransType().equalsIgnoreCase("CR")){
           				sum+=t1.getAmount().doubleValue();
           			}
           			else
           				sum-=t1.getAmount().doubleValue();
           		}
           	}out.println(sum);
           	%></h3>
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<p><a href="AddSponsor.jsp" name="Add Sponsor">Add Sponsor</a></p>
       	 
<p>&nbsp;</p>
        </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>


</body>
</html>