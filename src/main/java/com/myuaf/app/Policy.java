package com.example.defclass;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Policy
{
	
	public Policy(JSONObject obj) throws JSONException {
		if(obj != null) setPolicy(obj);
	}
	
	private void setPolicy(JSONObject objPolicy) throws JSONException {
		//Log.d("debugFidoUaf", "@setPolicy");
		if(!objPolicy.isNull("accepted")) {
			JSONArray objAccepted = objPolicy.optJSONArray("accepted");
			if(objAccepted != null) handleAccepted(objAccepted);
		}
		if(!objPolicy.isNull("disallowed")) {
			JSONArray objDisallowed = objPolicy.optJSONArray("disallowed");
			if(objDisallowed != null) handleDisallowed(objDisallowed);
		}
	}
	
	public boolean isValid() {
		if(accepted == null) return false;
		if(accepted.length == 0) return false;
		for(int i=0; i<accepted.length; ++i) {
			if(accepted[i].length > 0) return true;
		}
		
		return false;
	}
	
	public void print() {

	}
	
	private void handleAccepted(JSONArray objAccepted) throws JSONException {
		// construct list of MatchCriteria[]
		List<MatchCriteria[]> listOfListOfMatchCriteriaAccepted = new ArrayList<MatchCriteria[]>();
		
		for(int i=0; i<objAccepted.length(); ++i) {
			// column of accepted
			JSONArray objAcceptedI = objAccepted.optJSONArray(i);
			if(objAcceptedI == null) break;
			// construct list of MatchCriteria
			List<MatchCriteria> listOfM = new ArrayList<MatchCriteria>();
			for(int j=0; j<objAcceptedI.length(); ++j) {
				MatchCriteria tempMatchCriteria = new MatchCriteria();
				tempMatchCriteria.setMatchCriteria(objAcceptedI.optJSONObject(j));
				// add MatchCriteria to list
				listOfM.add(tempMatchCriteria);
			}
			// add list to list of list
			listOfListOfMatchCriteriaAccepted.add(listOfM.toArray(new MatchCriteria[listOfM.size()]));
		}
		// convert list to array: MatchCriteria[][]
		accepted = listOfListOfMatchCriteriaAccepted.toArray(new MatchCriteria[listOfListOfMatchCriteriaAccepted.size()][]);
	}
	
	
	private void handleDisallowed(JSONArray objDisallowed) throws JSONException {
		// construct list of MatchCriteria
		List<MatchCriteria> listOfMatchCriteriaDisallowed = new ArrayList<MatchCriteria>();
			
		for(int i=0; i<objDisallowed.length(); ++i) {
			MatchCriteria tempMatchCriteria = new MatchCriteria();
			tempMatchCriteria.setMatchCriteria(objDisallowed.optJSONObject(i));
			// add MatchCriteria to list
			listOfMatchCriteriaDisallowed.add(tempMatchCriteria);
		}
		// convert list to array: MatchCriteria[]
		disallowed = listOfMatchCriteriaDisallowed.toArray(new MatchCriteria[listOfMatchCriteriaDisallowed.size()]);
	}
	
	
	public MatchCriteria[][] accepted;	//required
	public MatchCriteria[] disallowed;
}

