package org.exemple.impl;

import org.exemple.impls.ExampleComponent;

import junit.framework.TestCase;

public class ExampleComponentTest extends TestCase {
	  public void testServiceImpl() {
		  ExampleComponent impl = new ExampleComponent();
		    assertEquals(4L, impl.sayHello("Joao"));
	  }
}
