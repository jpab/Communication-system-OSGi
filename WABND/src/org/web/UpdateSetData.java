package org.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.*;

//@Component(provide = Servlet.class, properties = {"alias=/update"})
public class UpdateSetData extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("fui chamado!!!!!!!!!CONTROLLER");
		PrintWriter out;
		BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
		
		String nameService = req.getParameter("nameService");
		String value = req.getParameter("valueService");
		
		out = resp.getWriter();
		
		if(Tracker.getSt()!=null && Tracker.getSt().getServiceReferences() != null){
			for(ServiceReference sr : Tracker.getSt().getServiceReferences()){
				if(((DeviceSensing)bc.getService(sr)).getName().equals(nameService)){
					((DeviceSensing)bc.getService(sr)).setValue(nameService, value);
					return;
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
	} 
}