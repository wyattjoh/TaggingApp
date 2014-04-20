package ca.ualberta.cs.taggingapp.models;

import android.content.Context;

/**
 * Manages the current user, login and logout as well as the authentication of
 * the user.
 * 
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

	/**
	 * Creates a new shared user model
	 */
	public static ActiveUserModel createShared(Context theContext) {
		if (singleton == null) {
			singleton = new ActiveUserModel(theContext);
		}

		return singleton;
	}

	/**
	 * Gets the shared user model
	 * 
	 * @return
	 */
	public static ActiveUserModel getShared() {
		if (singleton == null) {
			throw new RuntimeException(
					"Shared ActiveUserModel not created yet! Can't getShared().");
		}

		return singleton;
	}

	/**
	 * Perform the login for the email and the password
	 * 
	 * @param theEmail
	 * @param thePassword
	 * @return True if valid login, False if invalid login
	 */
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

	/**
	 * Perform authentication, currently return True
	 * 
	 * @param theUser
	 * @return
	 */
	public Boolean authenticateUser(User theUser) {
		return true;
	}

	/**
	 * Perform the logout of the user, removes all the shared instance data
	 */
	public void performLogout() {
		theUser = null;

		// Remove the user from preferences
		theSavedUserModel.remove();
		TagList.getInstance().remove();
		PictureList.getInstance().remove();
		RegionList.getInstance().remove();
	}

	/**
	 * @return True if logged in, false if not
	 */
	public Boolean isLoggedIn() {
		if (theUser == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets the user object
	 * 
	 * @return the user object
	 */
	public User getUser() {
		return theUser;
	}
}
