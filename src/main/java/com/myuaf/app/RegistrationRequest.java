package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.asmhandle.ASMRequest;
import com.example.asmhandle.Request;


public class RegistrationRequest
{	
	public RegistrationRequest(){
	}

//	public RegistrationRequest(JSONObject objMsg) throws JSONException{
//		//Log.d("debugFidoUaf", "@RegistrationRequest()");
//		header = new OperationHeader(objMsg.optJSONObject("header").toString());
//		challenge = objMsg.optString("challenge");
//		username = objMsg.optString("username");
//		JSONObject objPolicy = objMsg.optJSONObject("policy");
//		policy = new Policy(objPolicy);
//	}
	
	public String genRegASMRequest(String fcpString) throws JSONException {
		//Log.d("debugFidoUaf", "@RegistrationRequest/genRegASMRequest()");
		
		// version json
		JSONObject vJs = header.upv.toJSON();
		// args json
		JSONObject argsJs = genArgsJSON(fcpString);
		// asm request
		ASMRequest finalASMreq = new ASMRequest();
		String asmReq = finalASMreq.toASMString(Request.Register, vJs, authenticatorIndex, argsJs);
		
		return asmReq;
	}
	
	private JSONObject genArgsJSON(String fcpString) throws JSONException {
		JSONObject argsJs = new JSONObject();
		// appID TODO
		argsJs.put("appID", "https//myUAFTest");
		//argsJs.put("appID", req.header.appID);
		argsJs.put("username", username);
		argsJs.put("attestationType", 15879);
		argsJs.put("finalChallenge", fcpString);
		
		return argsJs;
	}
	
	public short isValid() {
		if (!header.isValid()) return PROTOCOL_ERROR;
		if (!header.upvIsSupported()) return UNSUPPORTED_VERSION;
		if ( (challenge == null) || challenge.equals("") || challenge.equals("null") ) return PROTOCOL_ERROR;
		// TODO base64
		if ( (challenge.getBytes().length < 8) || (challenge.getBytes().length > 64) ) return PROTOCOL_ERROR;
		if ( (username == null) || username.equals("")  || username.equals("null") || (username.length() > 128) ) return PROTOCOL_ERROR;
		if ( (policy == null)  || !policy.isValid() ) return PROTOCOL_ERROR;
		
		return NO_ERROR;
	}
	
	public void print() {

	}
	
	private int authenticatorIndex = 1;
	private short NO_ERROR = 0;
    private short UNSUPPORTED_VERSION = 4;
    private short PROTOCOL_ERROR = 6;
	
	public OperationHeader header;		//required
	public /*ServerChallenge*/String challenge;	//required
	public String username;			//required
	public Policy policy;			//required
}
