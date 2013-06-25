package org.test.plataform.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator{
	TestPlataform tp = null; 
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		tp = new TestPlataform(context);
		tp.regist();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		tp.unregist();
	}

	// TODO: class provided by template

}