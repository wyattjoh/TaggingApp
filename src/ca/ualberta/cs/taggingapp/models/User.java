package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

/**
 * A user class that models all properties of a user.
 * 
 * */

public class User {
	private String email = null;
	private String password = null;
	private ArrayList<Picture> pictures = new ArrayList<Picture>();
	private BoundingBoxSetting boundingBoxSetting = BoundingBoxSetting.DRAG;

	// Start of getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void addPicture(Picture picture) {
		this.pictures.add(picture);
	}

	public void removePicture(Picture picture) {
		this.pictures.remove(picture);
	}

	public BoundingBoxSetting getBoundingBoxSetting() {
		return boundingBoxSetting;
	}

	public void setBoundingBoxSetting(BoundingBoxSetting boundingBoxSetting) {
		this.boundingBoxSetting = boundingBoxSetting;
	}

	// End of getters and setters

	public Boolean checkPassword(String checkingPassword) {
		return this.password.equals(checkingPassword);
	}
}
