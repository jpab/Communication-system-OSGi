<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
  <h1>Welcome it's <%= new java.util.Date() %> </h1>
  
  <% 
  System.out.println(System.getProperty("SYSTEM DETAILS:"));
  String s;
  for(Object o : System.getProperties().keySet()){
	  s = (String)o;
	  %>
	  <h3>Propriadade</h3><%= s %><br>
	  Valor:<%= System.getProperty(s) %>
	  <br>
	  <% 
  }
  
  String jv = System.getProperty("java.version");
  System.out.println(jv);
  %>
  <a href="index.html">View the index page.</a>
  <h2>Details</h2>
  <%
  out.println(jv);
  out.println( "<BR>Your machine's address is " );
  out.println( request.getRemoteHost());
   %>
   <br>
   Vai incluir o index.html<br>
  <%@ include file="page2.jsp" %>
  <br>
   Vai incluir o index.html com a tag<br>
   <%@ page buffer="12kb" %>
  <%-- <jsp:forward page="page2.jsp"/> --%>
</body>
</html>