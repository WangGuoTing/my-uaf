package com.example.defclass;

public class Extension
{
	public Extension(String idIn, String dataIn, boolean b) {
		id = idIn;
		data = dataIn;
		fail_if_unknown = b;
	}
	
	public String id;		//required
	public String data;		//required
	public boolean fail_if_unknown;	//required
}