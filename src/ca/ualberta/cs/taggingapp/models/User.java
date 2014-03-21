package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class User {
	private String email = null;
	private String password = null;
	private ArrayList<Picture> pictures = new ArrayList<Picture>();
	private String boundingBoxSetting = "DEFAULT_TAP";
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
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
	public String getBoundingBoxSetting() {
		return boundingBoxSetting;
	}
	public void setBoundingBoxSetting(String boundingBoxSetting) {
		this.boundingBoxSetting = boundingBoxSetting;
	}
}
