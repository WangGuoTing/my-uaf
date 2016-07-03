package com.example.defclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrationResponse
{
	public RegistrationResponse(OperationHeader h, String f, String s) throws JSONException {
		header = h;
		fcParams = f;
		AuthenticatorRegistrationAssertion a = msgGetRegAssertion(s);
		AuthenticatorRegistrationAssertion[] atemp = {a}; // TODO
		assertions = atemp;
	}
	
	public RegistrationResponse(OperationHeader h, String f, AuthenticatorRegistrationAssertion a) {
		header = h;
		fcParams = f;
		AuthenticatorRegistrationAssertion[] atemp = {a}; // TODO
		assertions = atemp;
	}
	
	public String toResponseMsg() throws JSONException {
		//Log.d("debugFidoUaf", "@RegistrationResponse/toResponseMsg()");
		
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
	
	public AuthenticatorRegistrationAssertion msgGetRegAssertion(String s) throws JSONException {
		//Log.d("debugFidoUaf", "@RegistrationResponse/msgGetRegAssertion()");
		
		JSONObject objMsg = new JSONObject(s);
		
		int statusCode = objMsg.optInt("statusCode");
		if (statusCode != 0) return null; // TODO
		
		JSONObject responseData = objMsg.optJSONObject("responseData");
		String assertion = responseData.optString("assertion");
		String assertionScheme = responseData.optString("assertionScheme");
		
		AuthenticatorRegistrationAssertion authRegAssertion = new AuthenticatorRegistrationAssertion(assertion, assertionScheme);
		
		return authRegAssertion;
	}
	
	public void print() {

	}
	
	public OperationHeader header;		//required
	public String fcParams;				//required
	public AuthenticatorRegistrationAssertion[] assertions;	//required
}
