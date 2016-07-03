package com.example.asmhandle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.defclass.*;

// TODO opt?

public class ASMGetInfoOutHandle {
	public ASMGetInfoOutHandle(String s) throws JSONException {
		JSONObject objMsg = new JSONObject(s);
		
		// handle statusCode
		if(objMsg.has("statusCode")) {
			statusCode = objMsg.getInt("statusCode");
		}
		
		// handle responseData
		if(objMsg.has("responseData")) {
			JSONArray objData = objMsg.getJSONObject("responseData").getJSONArray("Authenticators");
			List<AuthenticatorInfo> listOfAuth = new ArrayList<AuthenticatorInfo>();
			for(int i=0; i< objData.length(); ++i) {
				listOfAuth.add(new AuthenticatorInfo(objData.getJSONObject(i)));
			}
			info = listOfAuth.toArray(new AuthenticatorInfo[listOfAuth.size()]);
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
	
	
	public AuthenticatorInfo[] info;
	//?
	public int statusCode;
	public Extension[] exts;
}
