<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.entities.*" %>
    <%@ page import="com.services.*" %>
    <%@page import="java.util.Date"%>
    <%@page import="javax.persistence.EntityManagerFactory"%>
	<%@page import="javax.persistence.Persistence"%>
	<%@page import="com.controller.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Tournament</title>
</head>
<body>
<%

Tournament t1=new Tournament();
t1.setTname("sampleTest");
t1.setType("Knockout");
t1.setStartdate(new Date());
t1.setEnddate(new Date());
t1.setStatus("Just Started");

//wbservice.addTournament(t1); 
/* EntityManagerFactory emf= Persistence.createEntityManagerFactory("TMC");
TournamentJpaController controller = new TournamentJpaController(emf);
try {
	controller.create(t1);
	System.out.println("Success");
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} */
%>
Success


</body>
</html>