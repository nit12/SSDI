<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Initiate New Tournament</title>
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
				<input type=hidden name="action" value="newTournament">
				<table width="559" height="183" border="0">
				  <tr>
				    <td>Tournament Name</td>
				    <td><input name="tname" type="text" /></td>
				  </tr>
				  <tr>
				    <td>Tournament Type</td>
				    <td><select name="ttype">
				    <option value="knockout">Knockout</option>
				  <option value="league">League</option></select></td>
				  </tr>
				  <tr>
				    <td>Start Date:</td>
				    <td><input type="date" name="sdate"/></td>
				  </tr>
				  <tr>
				    <td>End Date:</td>
				    <td><input type="date" name="edate"/></td>
				  </tr>
				  <tr>
				    <td>Finance Manager:</td>
				    <td><input name="fname" required value="Name" type="text" onBlur="if(this.value=='')this.value='Name'" onFocus="if(this.value=='Name')this.value='' "/></td>
				    <td><input type="email" required value="Email" name="femail" onBlur="if(this.value=='')this.value='Email'" onFocus="if(this.value=='Email')this.value='' "> <!-- JS because of IE support; better: placeholder="Email" --></td>
				  </tr>
				  <tr>
				    <td>Event Manager:</td>
				    <td><input name="ename" required value="Name" type="text" onBlur="if(this.value=='')this.value='Name'" onFocus="if(this.value=='Name')this.value='' "/></td>
				    <td><input type="email" required value="Email" name="eemail" onBlur="if(this.value=='')this.value='Email'" onFocus="if(this.value=='Email')this.value='' "> <!-- JS because of IE support; better: placeholder="Email" --></td>
				  </tr>
				  <tr>
				    <td>Staff Manager:</td>
				    <td><input name="sname" required value="Name" type="text" onBlur="if(this.value=='')this.value='Name'" onFocus="if(this.value=='Name')this.value='' "/></td>
				    <td><input type="email" required value="Email" name="semail" onBlur="if(this.value=='')this.value='Email'" onFocus="if(this.value=='Email')this.value='' "> <!-- JS because of IE support; better: placeholder="Email" --></td>
				  </tr>
				  <tr>
				  <td></td><td><input size="5" type="submit" type="submit" value="Create Tournament" /></td>
				  </tr>
				</table>				
				</form>


 </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>

</body>
</html>