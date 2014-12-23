<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.entities.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>leftpane</title>
</head>
<body>
<div id="leftmenu"> 

         <div id="leftmenu_top"></div> 

				<div id="leftmenu_main">    
                
                <h3>Navigation Links</h3>
                        
                <ul>
                <%
                 User sessionUser=(User)session.getAttribute("User");
                %>
                <!--if role is GM  -->
                <%if(sessionUser.getRole().equals("GM")){ %>
                    <li><a href="GMDashboard.jsp">DashBoard</a></li>
                    <li><a href="Supervision.jsp">Supervision</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                   <%}
                
                else if(sessionUser.getRole().equals("EM")){ %>
                	<li><a href="EMDashboard.jsp">DashBoard</a></li>
                    <li><a href="AddNoofTeams.jsp">Enter New Team</a></li>
                    <li><a href="ViewTeams.jsp">View Teams</a></li>
                    <li><a href="ScheduleDraw.jsp" >Schedule Draw</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                <%}
                
                else if(sessionUser.getRole().equals("FM")){ %>
                	<li><a href="FMDashboard.jsp">Home</a></li>
                	<li><a href="Sponsor.jsp">View & Add Sponsor</a></li>
                    <li><a href="Expenditure.jsp">View & Add Expenditure</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                <%}
                
                else if(sessionUser.getRole().equals("SM")){ %>
                	<li><a href="SMDashboard.jsp">Home</a></li>
                    <li><a href="CreateStaff.jsp">Create Staff</a></li>
                    <li><a href="AllocateStaff.jsp">Allocate Staff</a></li>
                    <li><a href="MaintainStaffSalary.jsp">Staff Maintenance</a></li>
                    <li><a href="logout.jsp">Logout</a></li>
                    
                <%}%>
                 
                    
                </ul>
</div>
                
                
              <div id="leftmenu_bottom"></div>
        </div>
</body>
</html>