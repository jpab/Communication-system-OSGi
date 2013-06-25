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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/slider.css" rel="stylesheet" media="screen">
<link href="css/switch.css" rel="stylesheet" media="screen">
 <link href="css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">


<title>BSense</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
	<style type="text/css"></style><style id="holderjs-style" type="text/css">.holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}</style><style type="text/css">@media print { #feedlyMiniIcon { display: none; } }</style>
 
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="img/faviconislab.png">
  

<!-- <script src="http://code.jquery.com/jquery.js"></script>-->
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
<!--  <script src="js/bootstrap-slider.js"></script> -->
<script src="js/slider-ss.js"></script>
<script src="js/switch.js"></script>
</head>
<body>

<script>
setInterval(function(){ 
$.get("update",  function(data) {
	//alert(data);
  //  if(data !== null && data.length > 0 && data === 1) {
    	//alert(3);
        document.location = document.location.href;
    //}
   // alert(2);
 });
},10000)

</script>

<script>

//$('#valueService').slider()
//.on('slide', function(ev){
//	var v = $(this).getValue();
//	alert(v);
//});
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
<div class="spacer30"></div>
    <div class="container marketing">
       <div class="row">
        <div class="span6">
        <h3>Controllers</h3>
     		<img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/services.png">
        	<%
        		if (bc ==  null) 
        			System.out.println("NULL BC");
          		else if(Tracker.getSct().getServiceReferences()!=null){
          			//System.out.println("NOT NULL BC2");
          			for(ServiceReference s : Tracker.getSct().getServiceReferences()){
          				//System.out.println("NOT NULL BC");
          		%>				
          		<div class="row serviceNameC">
          			<p><b><%=((DeviceController)bc.getService(s)).getName() %></b></p>
          		</div>
          		<div class="row serviceAttC">
          			<% for(String str : ((DeviceController)bc.getService(s)).getCollection().keySet()){	
          			%>
          			<div class="row">
		          		<div class="span2">
	          				<p><%=str%></p>
	          			</div>
	          			 	<div class="slider span3" >          					
          					</div>
          				</div>
          			</div>
          			<%
					} %>
          		</div>					
          		<% 
          			} //end for services
          		}
          	%><!-- /.row -->
        <div class="span6">
        <h3>Sensing</h3>
          <img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/servicessensing.png">
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
        
        </div>
      <!--</div> /.row -->
      </div><!-- /.container markting -->
      <div class="container marketing">
		<div class="row">
		<div class="span6">
        	 <img class="img-rounded" data-src="holder.js/140x140" alt="140x140" style="width: 140px; height: 140px;" src="img/devices.png">
  			<h2>Switch Services</h2>   
  			
          	<div class="row">
          	<%
          		if (bc ==  null) 
          				out.println("NULL BC");
          		else if(Tracker.getSs().getServiceReferences()!=null){
          			for(ServiceReference s : Tracker.getSs().getServiceReferences()){
          	%>			
				<div class="row">
				<div class="span6">
					<b><%=((SwitchService)bc.getService(s)).getName()%></b>
				</div>
				</div>
				<%
						for(String str : ((SwitchService)bc.getService(s)).getCollection().keySet()){															
							out.println("<div class=\"span3\">"+str+"</div>");
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
							
							 
							<input type="submit" value="Submit"/>
							 
							</form>
							<% 
							out.println("</div>");
						}
					}
				}
				%>
        		</div><!-- /.span6 -->
        	</div><!-- /.row -->
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
		</table>
        	</div><!-- /.span6 -->
      </div><!-- /.row -->
      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Back to top</a></p>
        <p>� 2013 ISLab. � <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Privacy</a> � <a href="http://twitter.github.io/bootstrap/examples/carousel.html#">Terms</a></p>
      </footer>

    
      </div><!-- /.container markting -->
      <script type="text/javascript">
	    jQuery(document).ready(function ($) {
	        $('#tabsservices').tab();
	    });
	</script> 
</body>
</html>