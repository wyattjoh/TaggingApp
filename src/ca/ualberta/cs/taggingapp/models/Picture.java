package ca.ualberta.cs.taggingapp.models;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import ca.ualberta.cs.taggingapp.controllers.BitmapWorkerTask;

/**
 * A picture class that models all properties of a picture.
 * 
 * */

public class Picture extends RegionObject {
	private transient Bitmap smallPicture = null;
	private transient Bitmap picture = null;
	private String picturePath = null;

	public Picture() {
		super();
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

	public Uri getPictureUri() {
		return Uri.parse(this.picturePath);
	}

	public void setPictureUri(Uri uri) {
		this.picturePath = uri.toString();
	}
	
	private void setBitmapOnImageViewForSize(ImageView theImageView, int theSize) {
		ImageLoadingFactory.loadBitmap(getPictureUri(), theImageView, theSize);
	}
	
	public void setLargeBitmapOnImageView(ImageView theImageView) {
		setBitmapOnImageViewForSize(theImageView, 2000);
	}

	public void setSmallBitmapOnImageView(ImageView theImageView) {
		setBitmapOnImageViewForSize(theImageView, 200);
	}
	
	public Bitmap getPicture() throws FileNotFoundException, IOException {
		if (picture == null) {
			// Load the picture from the URI
			picture = ImageLoadingFactory.decodeScaledBitmapFromUri(
					getPictureUri(), 2000);
		}

		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
}
