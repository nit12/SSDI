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
        	<h2><u>Current Tournament</u></h2>
<p>&nbsp;</p>
           	<table border="0" bordercolor="#000033" width="100%">
           	<tr><td><h3>ID</h3></td>
           	<td><h3>Name</h3></td>
           	<td><h3>Type</h3></td>
           	<td><h3>Start Date</h3></td>
           	<td><h3>End Date</h3></td>
           	<td><h3>Status</h3></td></tr>
           	
           	<%
           	wbservice wb=new wbservice();
           	User user=(User)session.getAttribute("User");
           	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           	List<Tournament> tour=wb.getTournaments();
           	if(null!=tour && tour.size()!=0)
           	{
		           	for(Tournament t: tour){
		           	if(t.getTid()==(user.getTid()).hashCode()){
		           	 %>
		           	<tr><td><%=t.getTid()%></td>
		           	<td><%=t.getTname()%></td>
		           	<td><%=t.getType() %></td>
		           	<td><%=format.format(t.getStartdate())%></td>
		           	<td><%=format.format(t.getEnddate())%></td>
		           	<td><%=t.getStatus() %></td></tr>
		           	 
		           	<%}}}
           	else
           	out.println("Currently there are no tournaments are scheduled. Kindly initiate a new tournament for better results.");
           	 %>
           	
           	</table>
           	<p>&nbsp;</p>
           	<%
           
           	List<Team> teamList=wb.getTeams();
           	List<Player> playerList=wb.getPlayers();
           	if(null!=teamList && teamList.size()!=0){
			 for(Team t: teamList)
			 {
		           	if(t.getTid().getTid()==(user.getTid()).getTid()){
			  %>
			  <table border="0" bordercolor="#000033" width="100%">
			  			<tr><td><h3>Team ID</h3></td>
			  			<td><h3>Team Name</h3></td>
			  			<td><h3>Team Seeding</h3></td>
			  			</tr>
			           	<tr><td><%=t.getTeamid() %></td>
           				<td><%=t.getTeamName() %></td>
           				<td><%=t.getTeamSeeding()%></td>
           				</tr>
           				<tr><td><h3>Player Name</h3></td>
           				<td><h3>Player DOB</h3></td>
           				<td><h3>Player Seeding in this team</h3></td>
           				</tr>
           		<%
           		playerList=t.getPlayerList();
           			for(Player p:playerList){
           			
		           			
		           		%>
		           				<tr><td><%=p.getPlayerName() %></td>
		           				<td><%=format.format(p.getDob()) %></td>           				
		           				<td><%=p.getSeeding()%></td>
		           				</tr>
           		
           		<%
           							
           			}
           		 %>
           		 </table>
           		 <p>&nbsp;</p>
           	   	<p>&nbsp;</p>
           		 <%}}}
           		 else
           		 out.println("No Teams Registered");
           		  %>
           	
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<p>&nbsp;</p>
           	<p>
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
   </div></div>


</body>
</html>