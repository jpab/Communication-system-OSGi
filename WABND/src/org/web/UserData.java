package org.web;

import org.osgi.framework.BundleContext;

public class UserData {
    
	String username;
    String email;
    int age;
    BundleContext bc; 

    public void setUsername( String value )
    {
        username = value;
    }

    public void setEmail( String value )
    {
        email = value;
    }

    public void setAge( int value )
    {
        age = value;
    }
    public void setBC( BundleContext bcaux )
    {
        bc = bcaux;
        System.out.println(bc.toString());
    }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public int getAge() { return age; }
}

