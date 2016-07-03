package com.example.asmhandle;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.defclass.Extension;

public class ASMRequest {
	
	public String toASMString(Request r, JSONObject v, int ind, JSONObject arg) throws JSONException {
		requestType = r.toString();
		asmVersion = v.toString();
		authenticatorIndex = ind;
		args = arg.toString();
		
		JSONObject asmReqJs = new JSONObject();
		asmReqJs.put("args", arg);
		asmReqJs.put("asmVersion", v);
		asmReqJs.put("authenticatorIndex", authenticatorIndex);
		asmReqJs.put("requestType", r);
		
		return asmReqJs.toString();
	}
	
    public String requestType; //  required 
    public String asmVersion;
    public int authenticatorIndex;
    public String args;
    Extension[]      exts;
}
