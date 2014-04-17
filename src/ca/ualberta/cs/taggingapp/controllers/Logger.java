package ca.ualberta.cs.taggingapp.controllers;

import java.util.Date;

import android.util.Log;

/**
 * A controller class that logs the users activity.
 * 
 */

public class Logger {
	private static LoggerAction theAction = new LoggerAction();

	public static void start(String theUser, String tagMethod) {

		long startTime = new Date().getTime();

		theAction.setStartTime(startTime);
		theAction.setTheUser(theUser);
		theAction.setTheMethod(tagMethod);

		Log.w("*", "");
		Log.w("start", "------------------------------------");
		Log.w("init_session", theUser);
		Log.w("tag_type", "" + tagMethod);
		Log.w("init_time", "" + startTime);
		Log.w("*", "");
	}

	public static void end() {

		long endTime = new Date().getTime();

		theAction.setEndTime(endTime);

		Log.w("*", "");
		Log.w("end_session", "");
		Log.w("terminate_time", "" + endTime);
		Log.w("end", "----------------------------------");
		Log.w("*", "");

		pushTestStats();
	}

	protected static void pushTestStats() {
		new LoggerAsyncTask().execute(theAction);
	}
}
