package org.fstjsf;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import aQute.bnd.annotation.component.*;

@Component(provide = Servlet.class, properties = {"alias=/"})
public class ExampleComponent extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.getWriter().append("Olá! Bem Vindo à página Inicial do meu projeto!");
		resp.getWriter().append("(Em construção)");
	}
}