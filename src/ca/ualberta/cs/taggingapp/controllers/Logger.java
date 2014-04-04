package ca.ualberta.cs.taggingapp.controllers;

import java.util.Date;

import android.util.Log;

public class Logger {

	public static void log(String tagType, String user, String action) {
		
		long timeStamp = new Date().getTime();
		
		Log.w(user, tagType + action + timeStamp);
		
		
	}
	
	public static void start(String user) {
		
		long timeStamp = new Date().getTime();
		Log.w("start", "------------");
		Log.w("init_session", user);
		Log.w("init_time", ""+timeStamp);
	}
	
	public static void end(String user) {
		
		long timeStamp = new Date().getTime();
		Log.w("end_session", user);
		Log.w("terminate_time", ""+timeStamp);
		Log.w("end", "------------");
	}
	
}
