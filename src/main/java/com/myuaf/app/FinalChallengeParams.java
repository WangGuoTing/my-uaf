package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;


public class FinalChallengeParams
{
	
	public FinalChallengeParams(String a, String ch, String f, ChannelBinding cb) {
		appID = a;
		challenge = ch;
		facetID = f;
		channelBinding = cb;
	}
	
	public String getFCP() throws JSONException {

		
		JSONObject js = new JSONObject();
		js.put("appID", appID);
		js.put("challenge", challenge);
		js.put("facetID", facetID);
		js.put("channelBinding", channelBinding);
		
		return js.toString();//(new sun.misc.BASE64Encoder()).encode(js.toString().getBytes());
		
		/*
		return Base64.encodeToString(gson.toJson(
				fcParams).getBytes(), Base64.URL_SAFE);
				*/
	}
	
	public String appID;			//required
	public String challenge;		//required
	public String facetID;			//required
	public ChannelBinding channelBinding;	//required
}
