<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Add Teams</title>
</head>
<body>
<div id="container">
		 <%@include file="header.jsp" %>
       
         <%@include file="site_navigation.jsp" %>
        
        <%@include file="leftpane_navigation.jsp" %>
        
        
        
        
		<div id="content">
        
        
        <div id="content_top"></div>
        <div id="content_main">
        <%
        String teamSize=request.getParameter("tsize");
        int tSize=Integer.parseInt(teamSize);
        String playerSize=request.getParameter("psize");
        int pSize=Integer.parseInt(playerSize);
         %>
				<form action="/TMC/TMCServlet" method="get" name="newTeam">
				<input type=hidden name="action" value="newTeam">
				 <input type=hidden name="tsize" value=<%=tSize%>>
				<input type=hidden name=psize value=<%=pSize%>>
				<%for(int i=1;i<=tSize;i++){ %>
				<h3>Team <%=i%>:-</h3>
				<table width="100%" height="20" border="0">
				
				  <tr>
				    <td>Team Name</td>
				    <td><input name="tname<%=i%>" type="text" /></td>
				  </tr>
				  <tr>
				    <td>Team Seeding</td>
				    <td><input name="tseeding<%=i%>" type="text" /></td></tr>
				  </table>
				  <%for(int j=1;j<=pSize;j++){ %>
				 <h3>Enter Player <%=j%> details:-</h3>
				<table width="100%" height="20" border="0">
				
				  <tr>
				    <td>Player Name</td>
				    <td><input name="pname<%=i%><%=j%>" type="text" /></td>
				  </tr>
				  <tr>
				    <td>Player DOB</td>
				    <td><input type="date" name="pDOBdate<%=i%><%=j%>"/></td>
				  </tr>
				  <tr>
				    <td>Player Ranking</td>
				    <td><input name="pseeding<%=i%><%=j%>" type="text" /></td>
				  </tr>
				  
				  </table>  
				  <%}
				  out.println("<p>&nbsp;</p>");
				  
				  } %>				  
				  <input size="5" type="submit" type="submit" value="Enter Teams" />
				  				
				</form>


 </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>

</body>
</html>