package ca.ualberta.cs.taggingapp.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

/**
 *  A picture class that models all parts of a picture.
 *  
 * */

public class Picture {
	private String id;
	private ArrayList<Region> regions;
	private transient Bitmap picture = null;
	private String picturePath = null;

	public Picture() {
		this.id = UUID.randomUUID().toString();
		this.regions = new ArrayList<Region>();
	}

	public Picture(Bitmap bitmap) {
		this();

		this.picture = bitmap;
	}

	public Picture(String uri) {
		this();

		this.picturePath = uri;
	}

	public Picture(Uri uri) {
		this();

		this.picturePath = uri.toString();
	}

	public Picture(Bitmap bitmap, String uri) {
		this();

		this.picture = bitmap;
		this.picturePath = uri;
	}

	// Start of getters and setters
	public ArrayList<Region> getRegions() {
		return regions;
	}

	public void addRegion(Region region) {
		this.regions.add(region);
	}

	public void removeRegion(Region region) {
		this.regions.remove(region);
	}

	public void removeAllRegions() {
		this.regions.clear();
	}

	public Uri getPictureUri() {
		return Uri.parse(this.picturePath);
	}

	public void setPictureUri(Uri uri) {
		this.picturePath = uri.toString();
	}

	public Bitmap getPicture() throws FileNotFoundException, IOException {
		if (picture == null && picturePath != null) {
			// TODO: Load the picture from the URI
			Log.w("Picture", "Photo loaded from factory");
			picture = ImageLoadingFactory.decodeScaledBitmapFromUri(
					getPictureUri(), 1000);
		}

		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	// End of getters and setters

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
