package org.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.*;

//@Component(provide = Servlet.class, properties = {"alias=/update"})
public class ChangeControllerService extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		DeviceController dc = null;
		
		String att = (String) req.getParameter("attribute");
		String serv = (String) req.getParameter("service");
		String value = (String) req.getParameter("value");
		
		
		BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
		if(Tracker.getSct()!=null && Tracker.getSct().getServiceReferences() != null){
			for(ServiceReference sr : Tracker.getSct().getServiceReferences()){
				dc = (DeviceController)bc.getService(sr);
				if(dc.getName().equals(serv)){
					dc.setValue(att, value);
					return;
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
	} 
}