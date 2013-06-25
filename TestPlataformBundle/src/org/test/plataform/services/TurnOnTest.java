package org.test.plataform.services;

import java.util.HashMap;
import java.util.Set;

import org.device.switchbtn.api.SwitchService;
	
public class TurnOnTest implements SwitchService{
	int changed = 1;
	private HashMap<String, Boolean> services=null;
	
	
	
	public TurnOnTest() {
		services = new HashMap<String, Boolean>();
		services.put("Open Smth Test", new Boolean(false));
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Little Test";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Test SS";
	}

	@Override
	public HashMap<String, Boolean> getCollection() {
		// TODO Auto-generated method stub
		return services;
	}

	@Override
	public Set<String> getProperties() {
		// TODO Auto-generated method stub
		return services.keySet();
	}

	@Override
	public Boolean getState(String s) {
		
		return services.get(s);
	}

	@Override
	public void setState(String s, Boolean b) {
		changed = 1;
		services.put(s, b);
		
	}

	@Override
	public int getChanged() {
		int rt =changed;
		changed=0;
		return rt;
	}

	@Override
	public void setChanged(int changed) {
		this.changed = changed;
	}
}
