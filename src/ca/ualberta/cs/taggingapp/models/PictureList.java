package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

/**
 * @author Wyatt Johnson
 */

public class PictureList {
	private static PictureList singleton = null;
	private ArrayList<Picture> pictureList;
	
	private PictureList() {
		this.pictureList = new ArrayList<Picture>();
	}
	
	public static PictureList getInstance() {
		if (singleton == null) {
			singleton = new PictureList();
		}
		return singleton;
	}
	
	/*
	 * Inserts the picture at the top of the list
	 */
	public void addPicture(Picture thePicture) {
		this.pictureList.add(0, thePicture);
	}
	
	/*
	 * Gets a picture from a specific position in the list
	 */
	public Picture getPicture(int position) {
		return this.pictureList.get(position);
	}
	
	/*
	 * Sets the picture list to the input
	 */
	public void setPictureList(ArrayList<Picture> thePictureList) {
		this.pictureList = thePictureList;
	}
	
	/*
	 * Gets the current picture list
	 */
	public ArrayList<Picture> getPictureList() {
		return this.pictureList;
	}
	
}
