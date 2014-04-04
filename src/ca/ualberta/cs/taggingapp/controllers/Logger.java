package ca.ualberta.cs.taggingapp.controllers;

import java.util.Date;

import ca.ualberta.cs.taggingapp.models.PictureList;

import android.util.Log;

public class Logger {

	public static void log(String tagType, String user, String action) {
		
		long timeStamp = new Date().getTime();
		Log.w(user, tagType + action + timeStamp);
		
	}
	
	public static void start(String user, int type) {
		
		long timeStamp = new Date().getTime();
		Log.w("*", "");
		Log.w("start", "------------------------------------");
		Log.w("init_session", user);
		Log.w("tag_type", ""+type);
		Log.w("init_time", ""+timeStamp);
		Log.w("*", "");
	}
	
	public static void end() {
		
		long timeStamp = new Date().getTime();
		Log.w("*", "");
		Log.w("end_session", "");
		Log.w("terminate_time", ""+timeStamp);
		Log.w("end", "----------------------------------");
		Log.w("*", "");
	}
	
	public static void event(String ev) {
		
		String pic = PictureList.getInstance().getSelected().toString();
		long timeStamp = new Date().getTime();
		Log.w("*", "");
		Log.w("		event", ev);
		Log.w("		picture", pic);
		Log.w("		timetime", ""+timeStamp);
		Log.w("*", "");
	}
	
}
