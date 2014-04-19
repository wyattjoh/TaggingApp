package ca.ualberta.cs.taggingapp.controllers;

/**
 * A controller class that logs the users activity.
 * 
 */

public class Logger {
	private static LoggerAction theAction = new LoggerAction();

	public static void start(String theUser, String tagMethod) {
		theAction.setStartTime();
		theAction.setTheUser(theUser);
		theAction.setTheMethod(tagMethod);
	}

	public static void end() {
		theAction.setEndTime();
		pushTestStats();
	}

	protected static void pushTestStats() {
		new LoggerAsyncTask().execute(theAction);
	}
}
