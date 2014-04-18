package ca.ualberta.cs.taggingapp.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.net.Uri;

/**
 *  Creates and manages a list of picture objects.
 * 
 * @author Wyatt Johnson
 */

public class PictureList extends SavedList<Picture> {
	private static PictureList singleton = null;
	private final static String FILENAME = "PictureListSaved.json";
	private Picture selectedPicture = null;
	private Context theContext;

	private PictureList(Context theContext) {
		super(theContext);
		this.theList = new ArrayList<Picture>();
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
		singleton.theList = singleton.load();
		return singleton;
	}

	public static PictureList getInstance() {
		if (singleton == null) {
			throw new RuntimeException("No instance created!");
		}
		return singleton;
	}

	public void updatePic(int index, Picture pic) {
		this.theList.set(index, pic);
		save();
	}

	/*
	 * Inserts the picture at the top of the list
	 */
	public void addPicture(Picture thePicture) {
		this.theList.add(0, thePicture);
		save();
	}

	/*
	 * Gets the photo at the designated location and adds it to the list
	 */
	public void addPicture(Uri location) {
		Picture newPicture = new Picture(location);
		this.theList.add(0, newPicture);
		save();
	}

	/*
	 * Gets a picture from a specific position in the list
	 */
	public Picture getPicture(int position) {
		return this.theList.get(position);
	}

	/*
	 * Sets the picture list to the input
	 */
	public void setPictureList(ArrayList<Picture> thePictureList) {
		this.theList = thePictureList;
		save();
	}

	/*
	 * Gets the current picture list
	 */
	public ArrayList<Picture> getPictureList() {
		return this.theList;
	}

	/*
	 * Sets the selected image
	 */
	public void setSelected(int position) {
		selectedPicture = this.theList.get(position);
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

	@Override
	public Picture[] getPrimativeArray(ArrayList<Picture> orignalArray) {
		return orignalArray.toArray(new Picture[0]);
	}

	@Override
	public Type getType() {
		return new TypeToken<Picture[]>() {
		}.getType();
	}
}
