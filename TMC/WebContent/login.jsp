<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login</title>

    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="style2.css" media="screen" type="text/css" />
    <!--[if lt IE 9]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>


		 
		 <div id="login-form"> 
		 
		 <%-- <%@include file="header.jsp" %> --%>

        <h3>Login</h3>

        <fieldset>

            <form action="/TMC/TMCServlet" method="get">
            <input type=hidden name="action" value="login">

                <input type="email" required value="Email" name="email" onBlur="if(this.value=='')this.value='Email'" onFocus="if(this.value=='Email')this.value='' "> <!-- JS because of IE support; better: placeholder="Email" -->

                <input type="password" required value="Password" name="password" onBlur="if(this.value=='')this.value='Password'" onFocus="if(this.value=='Password')this.value='' "> <!-- JS because of IE support; better: placeholder="Password" -->

                <input type="submit" value="Login">

                <footer class="clearfix">

                    <p><!-- <span class="info">*</span> --><%
String errorMessage=request.getParameter("errorMsg");
//out.println(errorMessage);
if(errorMessage!=null){
	
	out.println("Invalid Username/Password");
}


%></p>

                </footer>

            </form>

        </fieldset>

       <!-- end login-form -->
</div>
    
</body>
</html>