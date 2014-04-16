/**
 * 
 */
package ca.ualberta.cs.taggingapp.models;

import android.content.Context;

/**
 * @author wyatt
 * 
 */
public class ActiveUserModel {

	private static ActiveUserModel singleton = null;
	private User theUser = null;
	private SavedUserModel theSavedUserModel;

	private ActiveUserModel(Context theContext) {
		// Setup SavedUserModel
		this.theSavedUserModel = new SavedUserModel(theContext);

		// Load UserModel if already existing
		this.theUser = theSavedUserModel.load();
	}

	/*
	 * Creates a new shared object
	 */
	public static ActiveUserModel createShared(Context theContext) {
		if (singleton == null) {
			singleton = new ActiveUserModel(theContext);
		}

		return singleton;
	}

	public static ActiveUserModel getShared() {
		if (singleton == null) {
			throw new RuntimeException(
					"Shared ActiveUserModel not created yet! Can't getShared().");
		}

		return singleton;
	}

	public Boolean performLogin(String theEmail, String thePassword) {
		if (isLoggedIn()) {
			performLogout();
		}

		User theUserModel = new User();
		theUserModel.setEmail(theEmail);
		theUserModel.setPassword(thePassword);

		if (authenticateUser(theUserModel)) {
			this.theUser = theUserModel;

			// Save the user in preferences
			theSavedUserModel.save(theUser);

			return true;
		} else {
			return false;
		}

	}

	public Boolean authenticateUser(User theUser) {
		return true;
	}

	public void performLogout() {
		theUser = null;

		// Remove the user from preferences
		theSavedUserModel.remove();
		TagList.getInstance().remove();
		PictureList.getInstance().remove();
	}

	public Boolean isLoggedIn() {
		if (theUser == null) {
			return false;
		} else {
			return true;
		}
	}

	public User getUser() {
		return theUser;
	}
}
