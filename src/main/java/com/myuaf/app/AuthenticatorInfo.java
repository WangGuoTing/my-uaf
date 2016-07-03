package com.example.asmhandle;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.defclass.*;


public class AuthenticatorInfo {
	
	public AuthenticatorInfo(JSONObject o) throws JSONException {
		// authenticatorIndex
		authenticatorIndex = o.optInt("authenticatorIndex");
		
		// asmVersions
		List<Version> versionListTemp = new ArrayList<Version>();
		JSONArray objV = o.optJSONArray("asmVersions");
		for(int i=0; i<objV.length(); ++i) {
			versionListTemp.add(new Version(objV.optJSONObject(i).optInt("major"), objV.optJSONObject(i).optInt("minor")));
		}
		asmVersions = versionListTemp.toArray(new Version[versionListTemp.size()]);

		// isUserEnrolled
		isUserEnrolled = o.optBoolean("isUserEnrolled");
				
		// hasSettings
		hasSettings = o.optBoolean("hasSettings");
				
		// aaid
		aaid = o.optString("aaid");
		
		// assertionScheme
		assertionScheme = o.optString("assertionScheme");
				
		// authenticationAlgorithm
		authenticationAlgorithm = o.optInt("authenticationAlgorithm");
				
		// attestationTypes
		List<Integer> attAlgListTemp = new ArrayList<Integer>();
		JSONArray objAtt = o.optJSONArray("attestationTypes");
		for(int i=0; i<objAtt.length(); ++i) {
			attAlgListTemp.add(objAtt.optInt(i));
		}
		attestationTypes = convertIntegers(attAlgListTemp);
				
		// userVerification
		userVerification = o.optLong("userVerification");
		
		// keyProtection
		keyProtection = o.optInt("keyProtection");
		
		// matcherProtection
		matcherProtection = o.optInt("matcherProtection");
				
		// attachmentHint
		attachmentHint = o.optLong("attachmentHint");
				
		// isSecondFactorOnly
		isSecondFactorOnly = o.optBoolean("isSecondFactorOnly");
			
		// isRoamingAuthenticator
		isRoamingAuthenticator = o.optBoolean("isRoamingAuthenticator");
				
		// supportedExtensionIDs
		List<String> supExtListTemp = new ArrayList<String>();
		JSONArray objSupExt = o.optJSONArray("supportedExtensionIDs");
		for(int i=0; i<objSupExt.length(); ++i) {
			supExtListTemp.add(objSupExt.optString(i));
		}
		asmVersions = supExtListTemp.toArray(new Version[supExtListTemp.size()]);
				
		// tcDisplay
		tcDisplay = o.optInt("tcDisplay");
				
		// tcDisplayContentType
		tcDisplayContentType = o.optString("tcDisplayContentType");
				
		// TODO
		// DisplayPNGCharacteristicsDescriptor
				
		// title
		title = o.optString("title");
		
		// description
		description = o.optString("description");
				
		// icon
		icon = o.optString("icon");
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject js = new JSONObject();
		
		//authenticatorIndex
		js.put("authenticatorIndex", authenticatorIndex);
		
		//asmVersions[]
		JSONArray ja = new JSONArray();
		for(int i=0; i<asmVersions.length; ++i) {
			ja.put(asmVersions[i]);
		}
		js.put("asmVersions", ja);
		
		//isUserEnrolled
		js.put("isUserEnrolled", isUserEnrolled);
		
		//hasSettings
		js.put("hasSettings", hasSettings);
		
		//aaid
		js.put("aaid", aaid);
		
		//assertionScheme
		js.put("assertionScheme", assertionScheme);
		
		//authenticationAlgorithm
		js.put("authenticationAlgorithm", authenticationAlgorithm);
		
		//attestationTypes[]
		ja = new JSONArray();
		for(int i=0; i<attestationTypes.length; ++i) {
			ja.put(attestationTypes[i]);
		}
		js.put("attestationTypes", ja);
		
		//userVerification
		js.put("userVerification", userVerification);
		
		//keyProtection
		js.put("keyProtection", keyProtection);
		
		//matcherProtection
		js.put("matcherProtection", matcherProtection);
		
		//attachmentHint
		js.put("attachmentHint", attachmentHint);
		
		//isSecondFactorOnly
		js.put("isSecondFactorOnly", isSecondFactorOnly);
		
		//isRoamingAuthenticator
		js.put("isRoamingAuthenticator", isRoamingAuthenticator);
		
		//supportedExtensionIDs[]
		ja = new JSONArray();
		for(int i=0; i<supportedExtensionIDs.length; ++i) {
			ja.put(supportedExtensionIDs[i]);
		}
		js.put("supportedExtensionIDs", ja);
		
		//tcDisplay
		js.put("tcDisplay", tcDisplay);
		
		
		return js;
	}
	
	public void print() {

	}
	
	public Version[] getVersion() {
		return asmVersions;
	}
	
	// convert List<Integer> to int[]
	private int[] convertIntegers(List<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
	}
	
	
	private int			authenticatorIndex;		//required
	private Version[]	asmVersions;			//required
	private boolean		isUserEnrolled;			//required
	private boolean		hasSettings;			//required
	private String		aaid;					//required
	private String		assertionScheme;		//required
	private int			authenticationAlgorithm;//required
	private int[]		attestationTypes;		//required
	private long		userVerification;		//required
	private int			keyProtection;			//required
	private int			matcherProtection;		//required
	private long		attachmentHint;			//required
	private boolean		isSecondFactorOnly;		//required
	private boolean		isRoamingAuthenticator;	//required
	private String[]	supportedExtensionIDs;	//required
	private int			tcDisplay;				//required
	private String		tcDisplayContentType;
	private DisplayPNGCharacteristicsDescriptor[] tcDisplayPNGCharacteristics;
	private String		title;
	private String		description;
	private String		icon;
}
