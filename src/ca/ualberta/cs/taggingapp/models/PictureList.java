package ca.ualberta.cs.taggingapp.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.net.Uri;

/**
 * @author Wyatt Johnson
 */

public class PictureList extends SavedList<Picture> {
	private static PictureList singleton = null;
	private final static String FILENAME = "PictureListSaved.json";
	private ArrayList<Picture> pictureList;
	private Picture selectedPicture = null;
	private Context theContext;

	private PictureList(Context theContext) {
		super(theContext);
		this.pictureList = new ArrayList<Picture>();
	}

	/**
	 * @return the theContext
	 */
	public Context getTheContext() {
		return theContext;
	}

	/**
	 * @param theContext
	 *            the theContext to set
	 */
	public void setTheContext(Context theContext) {
		this.theContext = theContext;
	}

	public static PictureList createInstance(Context applicationContext) {
		singleton = new PictureList(applicationContext);
		singleton.setTheContext(applicationContext);
		singleton.pictureList = singleton.load();
		return singleton;
	}

	public static PictureList getInstance() {
		if (singleton == null) {
			throw new RuntimeException("No instance created!");
		}
		return singleton;
	}

	public void updatePic(int index, Picture pic) {
		this.pictureList.set(index, pic);
		save(this.pictureList);
	}

	/*
	 * Inserts the picture at the top of the list
	 */
	public void addPicture(Picture thePicture) {
		this.pictureList.add(0, thePicture);
		save(this.pictureList);
	}

	/*
	 * Gets the photo at the designated location and adds it to the list
	 */
	public void addPicture(Uri location) {
		Picture newPicture = new Picture(location);
		this.pictureList.add(0, newPicture);
		save(this.pictureList);
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
		save(this.pictureList);
	}

	/*
	 * Gets the current picture list
	 */
	public ArrayList<Picture> getPictureList() {
		return this.pictureList;
	}

	/*
	 * Sets the selected image
	 */
	public void setSelected(int position) {
		selectedPicture = this.pictureList.get(position);
	}

	/*
	 * Get the selected image
	 */
	public Picture getSelected() {
		return selectedPicture;
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}

	public Picture getPictureFromId(String theId) {
		for (Picture pic : this.pictureList) {
			if (pic.getId().equals(theId)) {
				return pic;
			}
		}
		return null;
	}

	@Override
	public Picture[] getPrimativeArray(ArrayList<Picture> orignalArray) {
		return orignalArray.toArray(new Picture[0]);
	}

	@Override
	public Type getType() {
		return new TypeToken<Picture[]>(){}.getType();
	}
}
