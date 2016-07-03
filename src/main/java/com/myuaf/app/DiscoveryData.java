package com.example.defclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.asmhandle.AuthenticatorInfo;


public class DiscoveryData {
	
	public DiscoveryData() {
		clientVendor = "NTU_FastCryptoLab"; // now it's name of my lab
		clientVersion = new Version(1,0);	// now it's v1.0 for my uaf client
	}
	
	// TODO if only one authenticator
	public DiscoveryData(AuthenticatorInfo info) {
		supportedUAFVersions = info.getVersion();
		clientVendor = "NTU_FastCryptoLab"; // now it's name of my lab
		clientVersion = new Version(1,0);	// now it's v1.0 for my uaf client
		AuthenticatorInfo[] atemp = {info};
		availableAuthenticators = atemp;
	}
	
	public JSONObject infoToJSON(JSONObject js) throws JSONException {
		JSONObject discoveryJs = new JSONObject();
		
		// version TODO
		Version v1 = new Version();
		discoveryJs.put("supportedUAFVersions", "[" + v1.toJSON().toString() + "]");
		
		// clientVendor
		discoveryJs.put("clientVendor", clientVendor);
		
		// clientVersion
		discoveryJs.put("clientVersion", clientVersion.toJSON().toString());
		
		// availableAuthenticators
		discoveryJs.put("availableAuthenticators", js);
		
		//Log.d("debugFidoUaf", "discoveryString = " + discoveryJs.toString());
		
		return discoveryJs;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject discoveryJs = new JSONObject();
		
		// version
		JSONArray ja = new JSONArray();
		for(int i=0; i<supportedUAFVersions.length; ++i) {
			ja.put(supportedUAFVersions[i].toJSON());
		}
		discoveryJs.put("supportedUAFVersions", ja);
		
		// clientVendor
		discoveryJs.put("clientVendor", clientVendor);
		
		// clientVersion
		discoveryJs.put("clientVersion", clientVersion);
		
		// availableAuthenticators
		ja = new JSONArray();
		for(int i=0; i<availableAuthenticators.length; ++i) {
			ja.put(availableAuthenticators[i].toJSON());
		}
		discoveryJs.put("availableAuthenticators", ja);
		
		//Log.d("debugFidoUaf", "discoveryString = " + discoveryJs.toString());
		
		return discoveryJs;
	}

	
	public Version[] supportedUAFVersions;	// required
	public String clientVendor;				// required
	public Version clientVersion;			// required
	public AuthenticatorInfo[] availableAuthenticators; // required
}
