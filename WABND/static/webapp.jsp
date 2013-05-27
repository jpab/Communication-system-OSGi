<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.osgi.framework.Bundle"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="org.osgi.framework.BundleContext"%>
<%@ page import="org.osgi.framework.FrameworkUtil"%>
<%@ page import="org.osgi.framework.ServiceReference" %>
<%@ page import="org.web.WebDataHandler" %>
<%@ page import="org.deviceservice.sensing.api.DeviceSensing" %>
<%@ page import="org.deviceservice.controller.api.DeviceController" %>
<%@ page import="wabnd.datahandler.api.DataHandlerService" %>
<%@ page import="org.web.UpdateData" %>
<%@ page import="org.web.Tracker" %>
<%
	BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
%> 
<%
 	WebDataHandler dh = new WebDataHandler(bc);
 %> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/slider.css" rel="stylesheet" media="screen">
<title>ISLabSensing</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="img/faviconislab.png">
  <style type="text/css"></style><style id="holderjs-style" type="text/css">.holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}</style><style type="text/css">@media print { #feedlyMiniIcon { display: none; } }</style>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-slider.js"></script>
</head>
<body>
<script>
setInterval(function(){ 
$.get("update",  function(data) {

	alert(i);
    if(data !== null && data.length > 0 && data === 1) {
        document.location = document.location.href;
    }
 });
},5000)

</script>
 <!-- NAVBAR
    ================================================== -->
    <div class="navbar-wrapper">
      <!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
      <div class="container">
	<a class="brand" href="index.html"> <img src="img/banner.jpg"></a>
        <div class="navbar navbar-inverse">
          <div class="navbar-inner">
            <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="brand" href="webapp.jsp">ISLab Sensing</a>
            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
            <div class="nav-collapse collapse">
              <ul class="nav">
                <li class="active"><a href="webapp.jsp">Home</a></li>
                <li><a href="http://twitter.github.io/bootstrap/examples/carousel.html#contact">Admin</a></li>
                <li><a href="about.html">About</a></li>
                <li><a href="contact.html">Contact</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->

      </div> <!-- /.container -->
    </div><!-- /.navbar-wrapper -->
<div class="spacer30"></div>
    <div class="container marketing">
      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="span6">
     		<img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/services.png">
        	<h2>Controller Services </h2>
        	<%
        		if (bc ==  null) 
          			out.println("NULL BC");
          		else if(Tracker.getSt().getServiceReferences()!=null){
          			for(ServiceReference s : Tracker.getSct().getServiceReferences()){
          		%>				
          		<div class="row" id = nameService>
          			<p><%=((DeviceController)bc.getService(s)).getName() %></p>
          		</div>
          		<div class="row">
          			<% for(String str : ((DeviceController)bc.getService(s)).getCollection().keySet()){	
          			%>
          				<div class="row" id=ServiceProp>
	          				<p><%=str%></p>
	          			</div> 	
	          		<div class="row">
          				<div class="slider-track" id = valueService>
          					<div class="slider-selection" style="left: 0%; width: <%=((DeviceController)bc.getService(s)).getValue(str) %>%;"></div>
          					<div class="slider-handle round" style="left: 0%;"></div>
          					<div class="slider-handle round hide" style="left: 0%;"></div>
          				</div>
          			</div>
          			<%
					} %>
          		</div>					
          		<% 
          			} //end for services
          		}
          	%>
        </div><!-- /.span6 -->
        <div class="span6">
          <img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/servicessensing.png">
          <h2>Services</h2>
          <p>Sensing Services</p>
           <table class="table table-hover">
          	<tr>
          		<td>
          		<b>Service</b>
          		</td>
          		<td>
          		<b>Sensing</b>
          		</td>
          		<td>
          		<b>Value</b>
          		</td>
          		
          	</tr>
          	<tr>
          	<%
          		if (bc ==  null) 
          				out.println("NULL BC");
          		else if(Tracker.getSt().getServiceReferences()!=null){
          			for(ServiceReference s : Tracker.getSt().getServiceReferences()){
          	%>			
				<td>
					<%=((DeviceSensing)bc.getService(s)).getName()%>
				</td>
				<td></td>
				<td></td>
				<%
						for(String str : ((DeviceSensing)bc.getService(s)).getCollection().keySet()){															
							out.println("<tr><td></td><td>"+str+"</td>");
							out.println("<td>"+((DeviceSensing)bc.getService(s)).getValue(str)+"</td></tr>");	
						}
					}
				}
				%>
				
			</tr>
		</table>
        </div><!-- /.span4 -->
      </div><!-- /.row -->
		<div class="row">
        	<div class="span6">
        	 <img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/devices.png">
          <h2>Devices</h2>
          <p>Devices Installed</p>
          <table class="table table-hover">
			<%
          		if (bc ==  null) 
          			out.println("NULL BC");
          		else{
          			for(String s : Tracker.getBndstracked().keySet()){
          				if(Tracker.getBndstracked().get(s).getHeaders().get("Device")!=null){
          	%><tr><td>
				<%=Tracker.getBndstracked().get(s).getSymbolicName()%>
			</td></tr>				
			<%
						}
					}
				}
			%>
			</td>
		</table>
        	</div><!-- /.span6 -->
      </div><!-- /.row -->
      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Back to top</a></p>
        <p>� 2013 ISLab. � <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Privacy</a> � <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Terms</a></p>
      </footer>

    </div>
</body>
</html>