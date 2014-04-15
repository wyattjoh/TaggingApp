package ca.ualberta.cs.taggingapp.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

public class Picture {
	private ArrayList<Region> regions;
	private transient Bitmap picture = null;
	private Uri pictureUri = null;

	public Picture() {
		this.regions = new ArrayList<Region>();
	}

	public Picture(Bitmap bitmap) {
		this.regions = new ArrayList<Region>();
		this.picture = bitmap;
	}

	public Picture(Uri uri) {
		this.regions = new ArrayList<Region>();
		this.pictureUri = uri;
	}

	public Picture(Bitmap bitmap, Uri uri) {
		this.regions = new ArrayList<Region>();
		this.picture = bitmap;
		this.pictureUri = uri;
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
		return this.pictureUri;
	}

	public void setPictureUri(Uri uri) {
		this.pictureUri = uri;
	}

	public Bitmap getPicture() throws FileNotFoundException, IOException {
		if (picture == null && pictureUri != null) {
			// TODO: Load the picture from the URI
			Log.w("Picture", "Photo loaded from factory");
			picture = ImageLoadingFactory.decodeScaledBitmapFromUri(pictureUri, 1000);
		}

		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
	// End of getters and setters
}
