package deepthinking.fgi.model;

import java.util.Map;

public class CalModel {
	private String jexlExp;
	private Map<String, Object> map;
	public String getJexlExp() {
		return jexlExp;
	}
	public void setJexlExp(String jexlExp) {
		this.jexlExp = jexlExp;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
