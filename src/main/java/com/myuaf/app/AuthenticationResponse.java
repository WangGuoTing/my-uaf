package com.example.defclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationResponse
{
	public AuthenticationResponse(OperationHeader h, String f, String s) throws JSONException {
		header = h;
		fcParams = f;
		AuthenticatorSignAssertion a = msgGetAuthAssertion(s);
		AuthenticatorSignAssertion[] atemp = {a}; // TODO
		assertions = atemp;
	}
	
	public AuthenticationResponse(OperationHeader h, String f, AuthenticatorSignAssertion a) {
		header = h;
		fcParams = f;
		AuthenticatorSignAssertion[] atemp = {a}; // TODO
		assertions = atemp;
	}
	
	public String toResponseMsg() throws JSONException {
		//Log.d("debugFidoUaf", "@AuthenticationResponse/toResponseMsg()");
		
		JSONObject js = new JSONObject();
		js.put("header", header.getHeaderJs());
		js.put("fcParams", fcParams);
		
		JSONArray jsa = new JSONArray();
		for(int i=0; i<assertions.length; ++i) {
			jsa.put(assertions[i].toJSONObject());
		}
		
		js.put("assertions", jsa);
		
		JSONArray jsarray = new JSONArray();
		jsarray.put(js);
		
		js = new JSONObject();
		js.put("uafProtocolMessage", jsarray);
		String s =  js.toString();
		
		return s;
	}
	
	public AuthenticatorSignAssertion msgGetAuthAssertion(String s) throws JSONException {
		//Log.d("debugFidoUaf", "@AuthenticationResponse/msgGetAuthAssertion()");
		
		JSONObject objMsg = new JSONObject(s);
		
		int statusCode = objMsg.optInt("statusCode");
		if (statusCode != 0) return null; // TODO
		
		JSONObject responseData = objMsg.optJSONObject("responseData");
		String assertion = responseData.optString("assertion");
		String assertionScheme = responseData.optString("assertionScheme");
		
		AuthenticatorSignAssertion authRegAssertion = new AuthenticatorSignAssertion(assertion, assertionScheme);
		
		return authRegAssertion;
	}
	
	public void print() {
		//Log.d ("debugFidoUaf", "header: " + header);
		//Log.d ("debugFidoUaf", "fcParams: " + fcParams);
		assertions[0].print();
	}
	
	public OperationHeader header;	//required
	public String fcParams;			//required
	public AuthenticatorSignAssertion[] assertions;	//required
}
