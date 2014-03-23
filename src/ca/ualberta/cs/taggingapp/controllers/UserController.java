package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.ActiveUserModel;

public class UserController {
	public static void logoutUser() {
		ActiveUserModel.getShared().performLogout();
	}

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
