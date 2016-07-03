package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;


public class AuthenticatorRegistrationAssertion
{
	
	public AuthenticatorRegistrationAssertion(String a, String as) {
		assertionScheme = as;
		assertion = a;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject js = new JSONObject();
		js.put("assertionScheme", assertionScheme);
		js.put("assertion", assertion);
		return js;
	}
	
	public void print() {

	}
	
	public String assertionScheme;		//required
	public String assertion;			//required
	public DisplayPNGCharacteristicsDescriptor[] tcDisplayPNGCharacteristics;
	public Extension[] exts;
}
