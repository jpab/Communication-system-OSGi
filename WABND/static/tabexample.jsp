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
<%@ page import="org.device.switchbtn.api.SwitchService" %>
<%@ page import="org.deviceservice.controller.api.DeviceController" %>
<%@ page import="wabnd.datahandler.api.DataHandlerService" %>
<%@ page import="org.web.UpdateData" %>
<%@ page import="org.web.Tracker" %>
<%
	BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
%> 
<%
 	WebDataHandler dh = new WebDataHandler(bc);
	//HttpSession sess = request.getSession();
	session.setAttribute("WebDataHandler", dh);
 %> 
<html>
<head>
  <!--<meta charset="utf-8"> -->
  <title>BSense</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

	<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
	<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
	<!--script src="js/less-1.3.3.min.js"></script-->
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="css/slider.css" rel="stylesheet" media="screen">
	<link href="css/switch.css" rel="stylesheet" media="screen">
	<link href="css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
  
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/scripts.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
	<script src="js/slider-ss.js"></script>
	<script src="js/switch.js"></script>

	
</head>

<body>
<div class="container-fluid">
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
            <a class="brand" href="webapp.jsp">BSense</a>
            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
            <div class="nav-collapse collapse">
              <ul class="nav">
                <li class="active"><a href="webapp.jsp">Home</a></li>
                <li><a href="actions.jsp">Actions</a></li>
                <li><a href="/system/console/bundles">Config</a></li>
                <li><a href="about.html">About</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div><!-- /.navbar-inner -->
        </div><!-- /.navbar -->

      </div> <!-- /.container -->
    </div><!-- /.navbar-wrapper -->
    <div class="container marketing">
	<div class="row-fluid">
		<div class="span12">
			<div class="page-header" align="center">
			<img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/services.png">
			
				<h1>
					Services
				</h1>
			</div>
			<div class="tabbable tabs-left" id="tabs-890604">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#sensetab" data-toggle="tab">Senses</a>
					</li>
					<li>
						<a href="#slidertab" data-toggle="tab">Sliders</a>
					</li>
					<li>
						<a href="#switchtab" data-toggle="tab">Switchs</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="sensetab">
							<table class="table table-hover">
					          	<tr>
					          		<td>
					          		<b>Service</b>
					          		</td>
					          		<td>
					          		<b>Attribute</b>
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
					</div>
					<div class="tab-pane" id="slidertab">
					<div class="span12">
				        	<%
				        		if (bc ==  null) 
				        			System.out.println("NULL BC");
				          		else if(Tracker.getSct().getServiceReferences()!=null){
				          			//System.out.println("NOT NULL BC2");
				          			for(ServiceReference s : Tracker.getSct().getServiceReferences()){
				          				//System.out.println("NOT NULL BC");
				          		%>				
				          		<div class="row serviceNameC">
				          			<div class="span4 offset1">
				          				<p><b><%=((DeviceController)bc.getService(s)).getName() %></b></p>
				          			</div>
				          		</div>
				          		<div class="row serviceAttC">
				          			<% for(String str : ((DeviceController)bc.getService(s)).getCollection().keySet()){	
				          			%>
				          			<div class="row">
						          		<div class="span2 offset1">
						          			<p><%=str%></p>
					          			</div>
					          			<div class="slider span4">
					          			
				          				</div>
				          			</div>
				          		</div>
				          			<%
									} %>	
				          		<% 
				          			} //end for services
				          		}
				          	%>
				          	</div>
					</div>
					<div class="tab-pane" id="switchtab">
						
				          	<%
				          		if (bc ==  null) 
				          				out.println("NULL BC");
				          		else if(Tracker.getSs().getServiceReferences()!=null){
				          			for(ServiceReference s : Tracker.getSs().getServiceReferences()){
				          	%>			
								<div class="row">
								<div class="span6 offset1">
									<b><%=((SwitchService)bc.getService(s)).getName()%></b>
								</div>
								</div>
								<%
										for(String str : ((SwitchService)bc.getService(s)).getCollection().keySet()){															
											out.println("<div class=\"span3 offset1\">"+str+"</div>");
											out.println("<div class=\"span3\">");
											%>
											<form action="" method="get">
											 
											
											<% if (((SwitchService)bc.getService(s)).getState(str).booleanValue()){
												System.out.println("ONNNNNN");
											%>
												<fieldset class="switch">
												<label class="off">Off<input type="radio" class="on_off" name="on_off" value="off"/></label>
											    <label class="on">On<input type="radio" class="on_off" name="on_off" value="on"/></label>
											    </fieldset>
											<%
											}
											else{
												System.out.println("OFFF");
												%>
												<fieldset class="switch">
												<label class="off">Off<input type="radio" class="on_off" name="on_off" value="off"/></label>
											    <label class="on">On<input type="radio" class="on_off" name="on_off" value="on"/></label>
												 </fieldset>
												<%
											}
											%>
											</form>
											<% 
											out.println("</div>");
										}
									}
								}
								%>
        				
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="page-header" align="center">
	 <img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/devices.png">
				<h1>
					Devices
				</h1>
			</div>
	<div class="row-fluid">
		<div class="span12">
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
		</table> 
		</div>
	</div>
	 <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Back to top</a></p>
        <p>© 2013 ISLab. · <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Privacy</a> · <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Terms</a></p>
      </footer>
	</div><!-- /.container markting -->
</div>
</body>
</html>
