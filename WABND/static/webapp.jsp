<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.osgi.framework.Bundle"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="org.osgi.framework.BundleContext"%>
<%@ page import="org.osgi.framework.FrameworkUtil"%>
<%@ page import="org.osgi.framework.ServiceReference" %>
<%@ page import="org.web.Tracker" %>
<% BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext"); %> 
<% Tracker tr = new Tracker(bc); %> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Aplication</title>
</head>
<body>
<table border="0" cellspacing="80">
<tr>
<th>Devices</th>
<th>Sensors</th>
<th>Actions</th>
</tr>
<tr>
  <td>
  <select name="FavWebSite" size="30">
  <% 
	if (bc ==  null) 
		out.println("NULL BC");
	else{
		for(String s : tr.getBndstracked().keySet()){
			if(tr.getBndstracked().get(s).getHeaders().get("Device")!=null){
				out.println("IKEEEE");
			%>
			<option value="SM"><%=tr.getBndstracked().get(s).getSymbolicName()%></option>
			<%
			}
		}
	}
%>
</select></td>
  <td>
  <select name="FavWebSite" size="30">
<% 
if (bc ==  null) 
	out.println("NULL BC");
else{
	for(String s : tr.getSrvstracked().keySet()){
			%>
			<option value="SM"><%= tr.getSrvstracked().get(s).getProperty("Name")%></option>
			<%	
	}
}
%>
</select>
  </td>
    <td>
  <select name="FavWebSite" size="30">
<option value="SM">http://www.scriptingmaster.com</option>
<option value="Google">http://www.google.com</option>
<option value="MSN">http://www.msn.com</option>
<option value="yahoo">http://www.yahoo.com</option>
<option value="microsoft">http://www.microsoft.com</option>
</select>
  </td>
</tr>
<tr>
   <td>
  <form id="searchbox" action="">
    <input id="search" type="text" placeholder="Type here">
    <input id="submit" type="submit" value="Search">
</form>
</td>
   <td>
  <form id="searchbox" action="">
    <input id="search" type="text" placeholder="Type here">
    <input id="submit" type="submit" value="Search">
</form>
</td>
   <td>
  <form id="searchbox" action="">
    <input id="search" type="text" placeholder="Type here">
    <input id="submit" type="submit" value="Search">
</form>
</td>
</tr>
</table>
</body>
</html>