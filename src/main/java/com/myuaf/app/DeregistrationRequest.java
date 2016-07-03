package com.example.defclass;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.asmhandle.ASMRequest;
import com.example.asmhandle.Request;


public class DeregistrationRequest
{	
	public DeregistrationRequest() {
		
	}
	
//	public DeregistrationRequest(JSONObject objMsg) throws JSONException {
//		header = new OperationHeader(objMsg.optJSONObject("header").toString());
//		List<DeregisterAuthenticator> listOfDeAu = new ArrayList<DeregisterAuthenticator>();
//		
//		if(!objMsg.isNull("authenticators")) {
//			JSONArray objAuth = objMsg.optJSONArray("authenticators");
//			if(objAuth != null){
//				for(int i=0; i<objAuth.length(); ++i) {
//					DeregisterAuthenticator t = new DeregisterAuthenticator(objAuth.optJSONObject(i));
//					listOfDeAu.add(t);
//				}
//				authenticators = listOfDeAu.toArray(new DeregisterAuthenticator[listOfDeAu.size()]);
//			}
//			else authenticators = null;
//		}
//		else authenticators = null;
//	}
	
	public String genDeregASMRequest() throws JSONException {
		//Log.d("debugFidoUaf", "@DeregistrationRequest/genDeregASMRequest()");
		
		// version json
		JSONObject vJs = header.upv.toJSON();
		// args json
		JSONObject argsJs = genArgsJSON();
		// asm request
		ASMRequest finalASMreq = new ASMRequest();
		String asmReq = finalASMreq.toASMString(Request.Authenticate, vJs, authenticatorIndex, argsJs);
	
		return asmReq;
	}
	
	private JSONObject genArgsJSON() throws JSONException {
		JSONObject argsJs = new JSONObject();
		// TODO
		argsJs.put("appID", "https//myUAFTest");
		//argsJs.put("appID", req.header.appID);
		argsJs.put("keyID", authenticators[0].keyID); // TODO: assume only delete one keyID
		
		return argsJs;
	}
	
	public short isValid() {
		if (!header.isValid()) return PROTOCOL_ERROR;
		if (!header.upvIsSupported()) return UNSUPPORTED_VERSION;
		if ( (authenticators == null) || (authenticators.length == 0) ) return PROTOCOL_ERROR;
		for(int i=0; i<authenticators.length; ++i) {
			if(!authenticators[i].isValid()) return PROTOCOL_ERROR;
		}
		return NO_ERROR;
	}
	
	public void print() {

	}
	
	private int authenticatorIndex = 1;
	private short NO_ERROR = 0;
    private short UNSUPPORTED_VERSION = 4;
    private short PROTOCOL_ERROR = 6;
	
	public OperationHeader header;						//required
	public DeregisterAuthenticator[] authenticators;	//required
}
