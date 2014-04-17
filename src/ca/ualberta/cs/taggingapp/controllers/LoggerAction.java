package ca.ualberta.cs.taggingapp.controllers;

public class LoggerAction {
	private String theMethod = "";
	private Long theTimeToFinish = 0L;
	
	
	public LoggerAction(String method, Long timeToFinish) {
		this.theMethod = method;
		this.theTimeToFinish = timeToFinish;
	}


	/**
	 * @return the theMethod
	 */
	public String getTheMethod() {
		return theMethod;
	}


	/**
	 * @param theMethod the theMethod to set
	 */
	public void setTheMethod(String theMethod) {
		this.theMethod = theMethod;
	}


	/**
	 * @return the theTimeToFinish
	 */
	public Long getTheTimeToFinish() {
		return theTimeToFinish;
	}


	/**
	 * @param theTimeToFinish the theTimeToFinish to set
	 */
	public void setTheTimeToFinish(Long theTimeToFinish) {
		this.theTimeToFinish = theTimeToFinish;
	}

}
