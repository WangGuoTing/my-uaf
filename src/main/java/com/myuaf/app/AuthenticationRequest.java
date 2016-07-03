package com.example.defclass;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.asmhandle.ASMRequest;
import com.example.asmhandle.Request;


public class AuthenticationRequest
{	
	public AuthenticationRequest(){

	}
	
//	public AuthenticationRequest(JSONObject objMsg) throws JSONException {
//		header = new OperationHeader(objMsg.optJSONObject("header").toString());
//		challenge = objMsg.optString("challenge");;
//		transaction = null;
//		JSONObject objPolicy = objMsg.optJSONObject("policy");
//		policy = new Policy(objPolicy);
//		
//		//tr = objMsg.optString("transaction");
//	}
	
	public String genAuthASMRequest(String fcpString) throws JSONException {
		//Log.d("debugFidoUaf", "@AuthenticationRequest/genAuthASMRequest()");
		
		// version json
		JSONObject vJs = header.upv.toJSON();
		// args json
		JSONObject argsJs = genArgsJSON(fcpString);
		// asm request
		ASMRequest finalASMreq = new ASMRequest();
		String asmReq = finalASMreq.toASMString(Request.Authenticate, vJs, authenticatorIndex, argsJs);

		return asmReq;
	}
	
	private JSONObject genArgsJSON(String fcpString) throws JSONException {
		JSONObject argsJs = new JSONObject();
		// TODO
		argsJs.put("appID", "https//myUAFTest");
		//argsJs.put("appID", req.header.appID);
		argsJs.put("finalChallenge", fcpString);
		
		return argsJs;
	}
	
	public short isValid() {
		if (!header.isValid()) return PROTOCOL_ERROR;
		if (!header.upvIsSupported()) return UNSUPPORTED_VERSION;
		if ( (challenge == null) || challenge.equals("") || challenge.equals("null") ) return PROTOCOL_ERROR;
		// TODO base64
		if ( (challenge.getBytes().length < 8) || (challenge.getBytes().length > 64) ) return PROTOCOL_ERROR;
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
	public Transaction[] transaction;
	public Policy policy;			//required
}
