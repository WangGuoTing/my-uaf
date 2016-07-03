package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;


public class DeregisterAuthenticator
{
	public DeregisterAuthenticator(JSONObject objAuth) throws JSONException {
		aaid = objAuth.optString("aaid");
		keyID = objAuth.optString("keyID");
	}
	
	public boolean isValid() {
		if( (aaid == null) || aaid.equals("")  || aaid.equals("null") ) return false;
		// aaid framat
		if(!aaidIsValid()) return false;
		if( (keyID == null) || keyID.equals("")  || keyID.equals("null") ) return false;
		if( (keyID.getBytes().length < 32) || (keyID.getBytes().length > 2048) ) return false;
		// keyID base64
		return true;
	}
	
	public void print() {

	}
	
	private boolean aaidIsValid() {
		if(aaid.length() != 9) return false;
		String aaidLow = aaid.toLowerCase();
		if( !((aaidLow.charAt(0)<='f')&&(aaidLow.charAt(0)>='a')) && !((aaidLow.charAt(0)<='9')&&(aaid.charAt(0)>='0')) ) return false;
		if( !((aaidLow.charAt(1)<='f')&&(aaidLow.charAt(1)>='a')) && !((aaidLow.charAt(1)<='9')&&(aaid.charAt(1)>='0')) ) return false;
		if( !((aaidLow.charAt(2)<='f')&&(aaidLow.charAt(2)>='a')) && !((aaidLow.charAt(2)<='9')&&(aaid.charAt(2)>='0')) ) return false;
		if( !((aaidLow.charAt(3)<='f')&&(aaidLow.charAt(3)>='a')) && !((aaidLow.charAt(3)<='9')&&(aaid.charAt(3)>='0')) ) return false;
		if( aaidLow.charAt(4) != '#' ) return false;
		if( !((aaidLow.charAt(5)<='f')&&(aaidLow.charAt(5)>='a')) && !((aaidLow.charAt(5)<='9')&&(aaid.charAt(5)>='0')) ) return false;
		if( !((aaidLow.charAt(6)<='f')&&(aaidLow.charAt(6)>='a')) && !((aaidLow.charAt(6)<='9')&&(aaid.charAt(6)>='0')) ) return false;
		if( !((aaidLow.charAt(7)<='f')&&(aaidLow.charAt(7)>='a')) && !((aaidLow.charAt(7)<='9')&&(aaid.charAt(7)>='0')) ) return false;
		if( !((aaidLow.charAt(8)<='f')&&(aaidLow.charAt(8)>='a')) && !((aaidLow.charAt(8)<='9')&&(aaid.charAt(8)>='0')) ) return false;
		
		return true;
	}
	
	public String aaid;		//required
	public String keyID;	//required
}
