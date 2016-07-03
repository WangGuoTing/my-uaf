package com.example.defclass;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MatchCriteria
{
	public void setMatchCriteria(JSONObject obj) throws JSONException {
		// aaid
		if(obj.has("aaid")) {
			List<String> aaidListTemp = new ArrayList<String>();
			JSONArray objAaid = obj.getJSONArray("aaid");
			for(int j=0; j<objAaid.length(); ++j) {
				aaidListTemp.add(objAaid.getString(j));
			}
			aaid = aaidListTemp.toArray(new String[aaidListTemp.size()]);
		}
		// vendor
		if(obj.has("vendor")) {
			List<String> vendorListTemp = new ArrayList<String>();
			JSONArray objVendor = obj.getJSONArray("vendor");
			for(int j=0; j<objVendor.length(); ++j) {
				vendorListTemp.add(objVendor.getString(j));
			}
			vendor = vendorListTemp.toArray(new String[vendorListTemp.size()]);
		}
		// keyIDs
		if(obj.has("keyIDs")) {
			List<String> keyIDsListTemp = new ArrayList<String>();
			JSONArray objKeyIDs = obj.getJSONArray("keyIDs");
			for(int j=0; j<objKeyIDs.length(); ++j) {
				keyIDsListTemp.add(objKeyIDs.getString(j));
			}
			keyIDs = keyIDsListTemp.toArray(new String[keyIDsListTemp.size()]);
		}
		// userVerification
		if(obj.has("userVerification")) {
			userVerification = obj.getLong("userVerification");
		}
		// keyProtection
		if(obj.has("keyProtection")) {
			keyProtection = obj.getInt("keyProtection");
		}
		// matcherProtection
		if(obj.has("matcherProtection")) {
			matcherProtection = obj.getInt("matcherProtection");
		}
		// attachmentHint
		if(obj.has("attachmentHint")) {
			attachmentHint = obj.getLong("attachmentHint");
		}
		// tcDisplay
		if(obj.has("tcDisplay")) {
			tcDisplay = obj.getInt("tcDisplay");
		}
		// authenticationAlgorithms
		if(obj.has("authenticationAlgorithms")) {
			List<Integer> authAlgListTemp = new ArrayList<Integer>();
			JSONArray objAuthAlg = obj.getJSONArray("authenticationAlgorithms");
			for(int j=0; j<objAuthAlg.length(); ++j) {
				authAlgListTemp.add(objAuthAlg.getInt(j));
			}
			authenticationAlgorithms = convertIntegers(authAlgListTemp);
		}
		// assertionSchemes
		if(obj.has("assertionSchemes")) {
			List<String> assertionSchemesListTemp = new ArrayList<String>();
			JSONArray objAssertionSchemes = obj.getJSONArray("assertionSchemes");
			for(int j=0; j<objAssertionSchemes.length(); ++j) {
				assertionSchemesListTemp.add(objAssertionSchemes.getString(j));
			}
			assertionSchemes = assertionSchemesListTemp.toArray(new String[assertionSchemesListTemp.size()]);
		}
		// attestationTypes
		if(obj.has("attestationTypes")) {
			List<Integer> attestationTypesListTemp = new ArrayList<Integer>();
			JSONArray objAttestationTypesListTemp = obj.getJSONArray("attestationTypes");
			for(int j=0; j<objAttestationTypesListTemp.length(); ++j) {
				attestationTypesListTemp.add(objAttestationTypesListTemp.getInt(j));
			}
			attestationTypes = convertIntegers(attestationTypesListTemp);
		}
		// authenticatorVersion
		if(obj.has("authenticatorVersion")) {
			authenticatorVersion = obj.getInt("authenticatorVersion");
		}
		// exts
		if(obj.has("exts")) {
			List<Extension> extsListTemp = new ArrayList<Extension>();
			JSONArray objExt = obj.getJSONArray("exts");
			for(int j=0; j<objExt.length(); ++j) {
				extsListTemp.add( new Extension(objExt.getJSONObject(j).getString("id"), 
						  						objExt.getJSONObject(j).getString("data"), 
						  						objExt.getJSONObject(j).getBoolean("fail_if_unknown")));
			}
			exts = extsListTemp.toArray(new Extension[extsListTemp.size()]);
		}
	}
	
	public void print() {

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
	
	public String[] aaid;
	public String[] vendor;
	public String[] keyIDs;
	public long userVerification;
	public int keyProtection;
	public int  matcherProtection;
	public long attachmentHint;
	public int  tcDisplay;
	public int [] authenticationAlgorithms;
	public String[] assertionSchemes;
	public int [] attestationTypes;
	public int  authenticatorVersion;
	public Extension[] exts;
}