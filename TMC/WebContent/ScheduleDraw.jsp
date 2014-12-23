<%@page import="com.services.wbservice"%>
<%@page import="com.controller.TournamentJpaController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@ page import="java.text.SimpleDateFormat" %>
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
        <p>Draw has been scheduled;</p>
        <p>Please fill the following match details:</p>
        <p>&nbsp;</p>
        
        <form action="/TMC/TMCServlet" method="get" name="newDraw">
				<input type=hidden name="action" value="newDraw">
				<table border="0" bordercolor="#000033" width="100%">
			           	<tr><td><h3>Match No</h3></td>
			           	<td><h3>Team1 Id</h3></td>
			           	<td><h3>Team2 Id</h3></td>
			           	<td><h3>Venue</h3></td>
			           	<td><h3>Match Date</h3></td>
			           	<!-- <td><h3>Result</h3></td> -->
			           	<td><h3>Round No</h3></td>
			           	<!-- <td><h3>Comments</h3></td>-->
			           	</tr> 
        <%
		User user=(User)session.getAttribute("User");
		wbservice wb=new wbservice();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Tournament tour=wb.findTournament(user.getTid().getTid());
		//List<Draw> drawList=wb.getDraws();
		List<Draw> drawList=tour.getDrawList();
		boolean drawCreated=true;
		boolean initiateDraw=false;
		if(drawList==null || drawList.size()==0){drawCreated=false;}
		int teamSize=tour.getTeamList().size();
		int minTeamID=Integer.MAX_VALUE;
			Team minTeam=new Team();
			List<Team> teamList=wb.getTeams();
           	if(null!=teamList && teamList.size()!=0){
           	for(Team t: teamList){
           		if(t.getTeamid().intValue()<minTeamID)
           		{
           			minTeamID=t.getTeamid().intValue();
           			minTeam=t;
           		}
           		}
           	}
           	System.out.println("min teamId is "+minTeamID);
		int[] adraw=new int[teamSize];
		adraw=wb.generateDraw(teamSize,minTeamID);
		if(!drawCreated){
		for(int i=0;i<teamSize;i+=2){
		
		initiateDraw=true;
		Draw d=new Draw();
		d.setTeam1Id(adraw[i]+"");
		d.setTeam2Id(adraw[i+1]+"");
		d.setTid(user.getTid());
		wb.addDraw(d);	
		
		}}
		drawList=wb.getDraws();
			if(null!=drawList && drawList.size()!=0)
           	{
           	for(Draw d: drawList){
		           	if(d.getTid().getTid()==(user.getTid()).getTid()){
           		
           	 %>

 						<input name="MatchNo<%=d.getMatchno()%>" type="hidden" value="<%=d.getMatchno()%>" />
			           	<tr><td><%=d.getMatchno()%></td>
			           	<td><%=d.getTeam1Id()%></td>
			           	<td><%=d.getTeam2Id()%></td>
			           	<td><input name="venue<%=d.getMatchno()%>" type="text" value="<%=d.getVenue()%>"></td>
			           	<td><input name="date<%=d.getMatchno()%>" type="date" value="<%=(d.getMatchDate()!=null)?format.format(d.getMatchDate()):new String("null")%>"></td>
			           	<%-- <td><%=d.getResult()%></td> --%>
			           	<td><input name="round<%=d.getMatchno().intValue()%>" type="text" readonly="readonly" value="1"></td></tr>
			           	<%-- <td><%=d.getComments() %></td></tr> --%>
			           	


<%}}} %>
         
					  </table>
				 <%if(initiateDraw){ %> <input size="5" type="submit" type="submit" value="Update Match Schedule" /><%} %>
				  				
				</form>


 </div>
        <div id="content_bottom"></div>
        <%@include file="footer.jsp" %>
   </div>

</body>
</html>