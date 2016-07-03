package com.example.defclass;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OperationHeader
{
	public OperationHeader(String s) throws JSONException {
		// TODO
		upv = new Version();
		stringHeader = s;
		JSONObject objH = new JSONObject(s);
		setOperationHeader(objH);
	}
	
	public void setOperationHeader(JSONObject objHeader) throws JSONException {
		
		// TODO
		
		JSONObject upvjs = objHeader.optJSONObject("upv");
		if(upvjs != null && !upvjs.isNull("major") && !upvjs.isNull("minor") ) {
			boolean maj = upvjs.optString("major").equals("");
			boolean min = upvjs.optString("minor").equals("");
			if(maj || min) upv = null;
			else upv = new Version(upvjs.optInt("major"),upvjs.optInt("minor"));
		}
		else upv = null;
		
		
		if(objHeader.has("appID")) {
			appID = objHeader.getString("appID");
		}
		if(objHeader.has("serverData")) {
			serverData = objHeader.getString("serverData");
		}
		if(objHeader.has("exts")) {
			List<Extension> extsListTemp = new ArrayList<Extension>();
			JSONArray objExt = objHeader.getJSONArray("exts");
			for(int j=0; j<objExt.length(); ++j) {
				Extension tempExt = new Extension(objExt.getJSONObject(j).getString("id"), 
												  objExt.getJSONObject(j).getString("data"), 
												  objExt.getJSONObject(j).getBoolean("fail_if_unknown"));
				extsListTemp.add(tempExt);
			}
			exts = extsListTemp.toArray(new Extension[extsListTemp.size()]);
		}
	}
	
	public JSONObject getHeaderJs() throws JSONException {
		JSONObject objH = new JSONObject(stringHeader);
		return objH;
	}
	
	public String getString() {
		return stringHeader;
	}
	
	public boolean upvIsSupported() {
		if(!upv.isValid()) return false;
		return true;
	}
	
	public boolean isValid() {
		if(upv == null) return false;
		if(appID == null || appID.equals("null") || appID.length() > 512 || appID.contains("http://")) return false;
		if(serverData != null && serverData.length() > 1536) return false;
		return true;
	}
	
	public void print() {

	}
	
	
	public String stringHeader;
	
	public Version upv;		//required
	public Operation op;	//required
	public String appID;
	public String serverData;
	public Extension[] exts;
}
