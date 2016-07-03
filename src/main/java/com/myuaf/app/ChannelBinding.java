package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;

public class ChannelBinding
{
	public ChannelBinding() {
		
	}
	
	public ChannelBinding(String s) {
		// TODO
	}
	
	public ChannelBinding(String sep, String tlsc, String tlsu, String cid) {
		serverEndPoint = sep;
		tlsServerCertificate = tlsc;
		tlsUnique = tlsu;
		cid_pubkey = cid;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject js = new JSONObject();
		js.put("serverEndPoint", serverEndPoint);
		js.put("tlsServerCertificate", tlsServerCertificate);
		js.put("tlsUnique", tlsUnique);
		js.put("cid_pubkey", cid_pubkey);
		return js;
	}
	
	public String serverEndPoint;
	public String tlsServerCertificate;
	public String tlsUnique;
	public String cid_pubkey;
}
