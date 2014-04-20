package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.ActiveUserModel;

/**
 * A controller that handles the users login information.
 * 
 * */

public class UserController {
	/**
	 * Performs a logout of the active user
	 */
	public static void logoutUser() {
		ActiveUserModel.getShared().performLogout();
	}

	/**
	 * Signs user up to service.
	 * 
	 * @param emailString
	 * @param passwordString
	 * @param confirmPasswordString
	 * @return
	 */
	public static Boolean signUpUser(String emailString, String passwordString,
			String confirmPasswordString) {

		if (passwordString.equals(confirmPasswordString)) {
			ActiveUserModel.getShared().performLogin(emailString,
					passwordString);
			return true;
		} else {
			return false;
		}
	}
}
