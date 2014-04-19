package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.BoundingBoxSetting;

/**
 * A controller class that logs the users activity.
 * 
 */

public class Logger {
	private static LoggerAction theAction = new LoggerAction();

	public static void start(String theUser, BoundingBoxSetting tagMethod) {
		theAction.setStartTime();
		theAction.setTheUser(theUser);
		theAction.setTheMethod(tagMethod.getName());
	}

	public static void end() {
		theAction.setEndTime();
		pushTestStats();
	}

	protected static void pushTestStats() {
		new LoggerAsyncTask().execute(theAction);
	}
}
