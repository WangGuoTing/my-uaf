package com.example.asmhandle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.defclass.Extension;

// TODO opt?

public class ASMGetRegistrationsOutHandle {
	public ASMGetRegistrationsOutHandle(String s) throws JSONException {
		JSONObject objMsg = new JSONObject(s);
		
		// handle statusCode
		if(objMsg.has("statusCode")) {
			statusCode = objMsg.getInt("statusCode");
		}
		
		// handle responseData
		if(objMsg.has("responseData")) {
			JSONArray objData = objMsg.getJSONObject("responseData").getJSONArray("appRegs");
			List<AppRegistration> listOfApp = new ArrayList<AppRegistration>();
			for(int i=0; i< objData.length(); ++i) {
				//listOfApp.add();//new AppRegistration(objData.getJSONObject(i)));
			}
			app = listOfApp.toArray(new AppRegistration[listOfApp.size()]);
		}
		
		// handle exts
		if(objMsg.has("exts")) {
			JSONArray objExt = objMsg.getJSONArray("exts");
			List<Extension> listOfExt = new ArrayList<Extension>();
			for(int i=0; i<objExt.length(); ++i) {
				listOfExt.add(new Extension(objExt.getJSONObject(i).getString("id"),
											objExt.getJSONObject(i).getString("data"),
											objExt.getJSONObject(i).getBoolean("fail_if_unknown")));
			}
			exts = listOfExt.toArray(new Extension[listOfExt.size()]);
		}
		
	}
	
	
	public AppRegistration[] app;
	//?
	public int statusCode;
	public Extension[] exts;
}
