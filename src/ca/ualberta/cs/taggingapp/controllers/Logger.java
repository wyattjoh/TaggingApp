package ca.ualberta.cs.taggingapp.controllers;

import java.util.Date;

import ca.ualberta.cs.taggingapp.models.PictureList;

import android.util.Log;

/**
 *  A controller class that logs the users activity.
 * 
 */

public class Logger {
	private static Long startTime = null;
	private static Long endTime = null;
	private static String method = null;

	public static void log(String tagType, String user, String action) {

		long timeStamp = new Date().getTime();
		Log.w(user, tagType + action + timeStamp);

	}

	public static void start(String user, long itemId) {

		startTime = new Date().getTime();
		method = Long.toString(itemId);
		Log.w("*", "");
		Log.w("start", "------------------------------------");
		Log.w("init_session", user);
		Log.w("tag_type", "" + itemId);
		Log.w("init_time", "" + startTime);
		Log.w("*", "");
	}

	public static void end() {

		endTime = new Date().getTime();
		Log.w("*", "");
		Log.w("end_session", "");
		Log.w("terminate_time", "" + endTime);
		Log.w("end", "----------------------------------");
		Log.w("*", "");

		pushTestStats();
	}

	protected static void pushTestStats() {
		if (method != null && startTime != null && endTime != null) {

			LoggerAction theAction = new LoggerAction(method, endTime
					- startTime);

			new LoggerAsyncTask().execute(theAction);

			resetRun();
		}
	}

	protected static void resetRun() {
		method = null;
		startTime = null;
		endTime = null;
	}

	public static void event(String ev) {

		String pic = PictureList.getInstance().getSelected().toString();
		long timeStamp = new Date().getTime();
		Log.w("*", "");
		Log.w("		event", ev);
		Log.w("		picture", pic);
		Log.w("		timetime", "" + timeStamp);
		Log.w("*", "");
	}

}
