package ca.ualberta.cs.taggingapp.controllers;

import java.util.Date;

public class LoggerAction {
	private String theMethod;
	private Long startTime;
	private Long endTime;
	private String theUser;

	public LoggerAction() {
		this.theMethod = "";
		this.startTime = 0L;
		this.endTime = 0L;
		this.theUser = "";
	}

	/**
	 * @return the theMethod
	 */
	public String getTheMethod() {
		return theMethod;
	}

	/**
	 * @param theMethod
	 *            the theMethod to set
	 */
	public void setTheMethod(String theMethod) {
		this.theMethod = theMethod;
	}

	/**
	 * @return the theTimeToFinish
	 */
	public Long getTheTimeToFinish() {
		return endTime - startTime;
	}

	/**
	 * @return the theUser
	 */
	public String getTheUser() {
		return theUser;
	}

	/**
	 * @param theUser
	 *            the theUser to set
	 */
	public void setTheUser(String theUser) {
		this.theUser = theUser;
	}

	/**
	 * @return the startTime
	 */
	public Long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime() {
		this.startTime = new Date().getTime();
	}

	/**
	 * @return the endTime
	 */
	public Long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime() {
		this.endTime = new Date().getTime();
	}
}
