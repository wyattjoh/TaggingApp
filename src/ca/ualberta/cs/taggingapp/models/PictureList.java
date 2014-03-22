package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

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
	
	
}
