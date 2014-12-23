<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.entities.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="menu">
        	<ul>
            	
                <li class="menuitem"><a href="#">About</a></li>
                <li class="menuitem"><a href="#">Help</a></li>
              <li class="menuitem"><a href="#">Contact</a></li>
              
            </ul>
            <h2><!-- <input id="placeholder" type="url" placeholder="not signed in"> -->
            <%
            User u=(User)session.getAttribute("User");
            out.println(u.getName());           
            %>
            </h2>
        </div>  
</body>
</html>