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
<%@ page import="org.device.action.api.ActionService" %>
<%@ page import="org.device.switchbtn.api.SwitchService" %>
<%@ page import="wabnd.datahandler.api.DataHandlerService" %>
<%@ page import="org.web.UpdateData" %>
<%@ page import="org.web.Tracker" %>
<%
	BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
	WebDataHandler dh = (WebDataHandler)session.getAttribute("WebDataHandler");
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
//script to update
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
                <li><a href="actions.jsp">Actions</a></li>
                <li><a href="/system/console/bundles">Admin</a></li>
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
		<div class="row">
        	<div class="span9">
        	 <img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/devices.png">
          <h2>Actions</h2>
          <p>Actions Installed</p>
          <table class="table table-hover">
			<%
          		if(Tracker.getAt()!=null){
          			System.out.println(Tracker.getAt().getServiceReferences());
          			if(Tracker.getAt().getServiceReferences()!=null){
          			for(ServiceReference sr : Tracker.getAt().getServiceReferences()){
          				out.println("<tr><td>");
          				//out.println(((ActionService)bc.getService(sr)).getName());
          				out.println("</td><td>");
          			//	out.println(((ActionService)bc.getService(sr)).getActuator());
          				out.println("</td><td>");
          				%>
          			 <div class="btn-group">
          			  		<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Actuators
          			    	<span class="caret"></span>
          			  		</a>
          			  		<ul class="dropdown-menu">
	          			<%
	          				for(ServiceReference sr2 : ((ActionService)bc.getService(sr)).getActuators()){
	          					out.print("<li>");
	          					Class<?> aux = ((ActionService)bc.getService(sr)).getC();
	          					if(aux.equals(SwitchService.class)){
	          						out.println(((SwitchService)bc.getService(sr2)).getName());
	          					} else if(aux.equals(DeviceSensing.class)){
	          						out.println(((DeviceSensing)bc.getService(sr2)).getName());
	          					}else if(aux.equals(DeviceController.class)){
	          						out.println(((DeviceController)bc.getService(sr2)).getName());
	          					}
	          					out.print("</li>");
	          				}
	          			%>
          			   		 	<!-- dropdown menu links -->
          			  		</ul>
          				</div>
          				<%
          				//put in a combo box
          				out.println("</td></tr>");
          			}
          			}
          		}
			%>
		</table>
        	</div><!-- /.span9 -->
        	<div class="span3">
        	
        	
        	</div><!-- /.span3 -->
      </div><!-- /.row -->
      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Back to top</a></p>
        <p>� 2013 ISLab. � <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Privacy</a> � <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Terms</a></p>
      </footer>

    </div>
</body>
</html>