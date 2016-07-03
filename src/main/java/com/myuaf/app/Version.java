package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;

public class Version
{
	public Version() {
		major = 1;
		minor = 0;
	}
	
	public Version(int maj, int min){
		major = maj;
		minor = min;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject js = new JSONObject();
		js.put("major", major);
		js.put("minor", minor);
		return js;
	}
	
	public boolean isValid() {
		if( major != 1 || minor != 0 ) return false;
		return true;
	}
	
	private int major;	//readonly
	private int minor;	//readonly

}