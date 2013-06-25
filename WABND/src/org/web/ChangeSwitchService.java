package org.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.device.switchbtn.api.SwitchService;
import org.deviceservice.controller.api.DeviceController;
import org.deviceservice.sensing.api.DeviceSensing;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import aQute.bnd.annotation.component.*;

//@Component(provide = Servlet.class, properties = {"alias=/update"})
public class ChangeSwitchService extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		SwitchService ss = null;
		
		Boolean b = null;
		String att = (String) req.getParameter("attribute");
		String serv = (String) req.getParameter("service");
		String value = (String) req.getParameter("value");
		
		
		if(value.equals("off")){
			b = new Boolean(false);
		}else{
			b = new Boolean(true);
		}
		
		BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
		if(Tracker.getSs()!=null && Tracker.getSs().getServiceReferences() != null){
			for(ServiceReference sr : Tracker.getSs().getServiceReferences()){
				ss = (SwitchService)bc.getService(sr);
				if(ss.getName().equals(serv)){
					ss.setState(att, b);
					return;
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
	} 
}