package com.example.defclass;

import org.json.JSONException;

public class UAFClientActivity{
	
	private static final int REG_ACTIVITY = 1;
    private static final int AUTH_ACTIVITY = 2;
    private static final int DEREG_ACTIVITY = 3;
    private static final int REGINFO_ACTIVITY = 4;
    private static final int ASMINFO_ACTIVITY = 5;
    
    private static final short NO_ERROR = 0;
    private static final short WAIT_USER_ACTION = 1;
    private static final short INSECURE_TRANSPORT = 2;
    private static final short USER_CANCELLED = 3;
    private static final short UNSUPPORTED_VERSION = 4;
    private static final short NO_SUITABLE_AUTHENTICATOR = 5;
    private static final short PROTOCOL_ERROR = 6;
    private static final short UNTRUSTED_FACET_ID = 7;
    private static final short UNKNOWN = 255;
	
	/**
	 * parse Client request, generate ASM request and send to ASM app
	 * @param msg
	 * @throws JSONException
	 */
	public short handleRequest(String msg, String channelBindings) throws JSONException {
//		JSONArray objMsgArray = new JSONArray(msg);
//		if(objMsgArray == null || objMsgArray.length() != 1) return PROTOCOL_ERROR;
//		
//		JSONObject objMsg = objMsgArray.optJSONObject(0);
//		JSONObject objHeader = objMsg.optJSONObject("header");
		
		// check operation first, and return error message
//		if(objHeader == null)  return PROTOCOL_ERROR;
//		else if(objHeader.isNull("op")) return PROTOCOL_ERROR;
//		else {
//			staticHeader = objHeader.toString();
//			if(staticHeader.equals("")) return PROTOCOL_ERROR;
//		}

		// check the operation type and generate corresponding request
		if(true) {
			// generate request and check if request is valid
			//RegistrationRequest regReq = new RegistrationRequest();
			//short reqIsValid = regReq.isValid();
			//if(reqIsValid != NO_ERROR) return reqIsValid;
			
			// calculate final challenge
			//FinalChallengeParams fcp = new FinalChallengeParams(regReq.header.appID, regReq.challenge, "", new ChannelBinding(channelBindings));
			//staticFC = fcp.getFCP();
			
			// send asm request
			//String asmReqString = regReq.genRegASMRequest(staticFC);

		}
		else if(true) {
			// generate request and check if request is valid
			AuthenticationRequest authReq = new AuthenticationRequest();
			short reqIsValid = authReq.isValid();
			if(reqIsValid != NO_ERROR) return reqIsValid;
			
			// calculate final challenge
			FinalChallengeParams fcp = new FinalChallengeParams(authReq.header.appID, authReq.challenge, "", new ChannelBinding(channelBindings));
			staticFC = fcp.getFCP();
			
			// send asm request
			String asmReqString = authReq.genAuthASMRequest(staticFC);

		}
		else if(true) {
			// generate request and check if request is valid
			DeregistrationRequest deregReq = new DeregistrationRequest();
			short reqIsValid = deregReq.isValid();
			if(reqIsValid != NO_ERROR) return reqIsValid;
			
			// send asm request
			String asmReqString = deregReq.genDeregASMRequest();

		}
		else return UNKNOWN;
		
		// TODO check if no problem
		// if no else above, but return unknown, will lead to error when call asm
		
		return NO_ERROR;
	}


	static String staticHeader;
	static String staticFC;
	
	/**
	 * handle the intent from RP Client, different case of intentType
	 * @param intentType
	 * @param i
	 * @throws JSONException
	 */
	private void handleIntent(String intentType) throws JSONException {

		
		// different intentType
		if(intentType.equals("DISCOVER")) {
			
		}
		else if (intentType.equals("CHECK_POLICY")) {
			String message;
		}
		else if (intentType.equals("UAF_OPERATION")) {
			String message = "";
			String channelBindings = "";
			
			String requestString = "";
			
			short errorCode = handleRequest(requestString,channelBindings);
			
		}
		else if (intentType.equals("UAF_OPERATION_COMPLETION_STATUS")) {
			String message;
			String responseCode;
		}
		else {
			// intent
			// finish()
		}
	}
	
	

	

	/**
     * Catch any replies to intents that we sent out
     */
	protected void onActivityResult(int requestCode, int resultCode) {


		/*
		 * get message and parse it
		 */
		
		// TODO resultCode? 
		
		String respString = "";

		
		switch(requestCode) {
			case REG_ACTIVITY:

				if (true) {
					String message = "";

					try {
						// generate response
						RegistrationResponse resp = new RegistrationResponse(new OperationHeader(staticHeader), staticFC, message);
						respString = resp.toResponseMsg();

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
				
			case AUTH_ACTIVITY:

				if (true) {
					String message = "";
					try {
						// generate response
						AuthenticationResponse resp = new AuthenticationResponse(new OperationHeader(staticHeader), staticFC, message);
						respString = resp.toResponseMsg();

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
				
			case DEREG_ACTIVITY:
				break;
				
			case REGINFO_ACTIVITY:
				break;
				
			case ASMINFO_ACTIVITY:

				if (true) {
					String message = "";
//						JSONObject jsMsg = new JSONObject(message);
//						JSONArray authenticators = jsMsg.getJSONObject("responseData").getJSONArray("Authenticators");
						// TODO if more than 1 authenticator
						//AuthenticatorInfo asmInfo = new AuthenticatorInfo(authenticators.getJSONObject(0));
						DiscoveryData discovery = new DiscoveryData();
						//String discoveryString = discovery.toJSON().toString();
						String discoveryString = "";//discovery.infoToJSON(authenticators.getJSONObject(0)).toString();
				}
				break;
		}
	}

	
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */

	/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uafclient);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		Intent intent = getIntent();
		String intentType = intent.getStringExtra("UAFIntentType");
		
		if(intentType != null) {
			try {
				handleIntent(intentType,intent);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		//getInfo();
		//testReq();
		//ASMcntReg();
		//getFIDOapplication();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.demo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 *
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_uafclient, container,
					false);
			return rootView;
		}
	}
	*/

}
