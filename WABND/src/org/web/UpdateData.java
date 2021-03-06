package org.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
public class UpdateData extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int change=1;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		PrintWriter out;
		BundleContext bc = (BundleContext)this.getServletContext().getAttribute("osgi-bundlecontext");
		RequestDispatcher disp;
		out = resp.getWriter();
		if(Tracker.getSt()!=null && Tracker.getSt().getServiceReferences() != null){
			for(ServiceReference sr : Tracker.getSt().getServiceReferences()){
				if(((DeviceSensing)bc.getService(sr)).getChanged()==1){
					out.println("1");
					System.out.println("fui chamado111111111111");
					//resp.sendRedirect("WABND/webapp.jsp");
					disp =req.getRequestDispatcher("/actions.jsp"); 
					disp.forward(req, resp);
					System.out.println("fui chamado111111111111");
					//try request here
					return;
				}
			}
			System.out.println("fui chamado00000000000");

			disp =req.getRequestDispatcher("/actions.jsp");
			disp.forward(req, resp);
			out.println("0");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
	} 
}