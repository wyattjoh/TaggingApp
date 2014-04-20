package ca.ualberta.cs.taggingapp.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.net.Uri;

/**
 * Creates and manages a list of picture objects.
 * 
 * @author wyatt
 */

public class PictureList extends SavedList<Picture> {
	private static PictureList singleton = null;
	private final static String FILENAME = "PictureListSaved.json";
	private Picture selectedPicture = null;
	private Context theContext;

	private PictureList(Context theContext) {
		super(theContext);
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

	/**
	 * Updates the picture in the list
	 * @param index
	 * @param pic
	 */
	public void updatePic(int index, Picture pic) {
		this.theList.set(index, pic);
		save();
	}

	/**
	 * Gets the photo at the designated location and adds it to the list
	 */
	public void add(Uri location) {
		add(new Picture(location));
	}

	/**
	 * Sets the selected image
	 */
	public void setSelected(int position) {
		Picture thePicture = this.theList.get(position);
		setSelected(thePicture);
	}
	
	/**
	 * Sets the image as selected
	 * @param thePicture
	 */
	public void setSelected(Picture thePicture) {
		selectedPicture = thePicture;
	}

	/**
	 * Get the selected image
	 */
	public Picture getSelected() {
		return selectedPicture;
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.taggingapp.models.SavedList#getFilename()
	 */
	@Override
	public String getFilename() {
		return FILENAME;
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.taggingapp.models.SavedList#getPrimativeArray(java.util.ArrayList)
	 */
	@Override
	public Picture[] getPrimativeArray(ArrayList<Picture> orignalArray) {
		return orignalArray.toArray(new Picture[0]);
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.taggingapp.models.SavedList#getType()
	 */
	@Override
	public Type getType() {
		return new TypeToken<Picture[]>() {
		}.getType();
	}
}
