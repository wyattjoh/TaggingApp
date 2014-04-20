package ca.ualberta.cs.taggingapp.models;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

/**
 * A picture class that models all properties of a picture.
 * 
 * */

public class Picture extends RegionObject {
	private transient Bitmap picture = null;
	private String picturePath = null;

	public Picture() {
		super();
	}

	public Picture(String uri) {
		this();

		this.picturePath = uri;
	}

	public Picture(Uri uri) {
		this();

		this.picturePath = uri.toString();
	}

	/**
	 * Sets the picture
	 * @param picture
	 */
	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	/**
	 * @return the picture uri
	 */
	public Uri getPictureUri() {
		return Uri.parse(this.picturePath);
	}

	/**
	 * Sets the picture URI
	 * @param uri
	 */
	public void setPictureUri(Uri uri) {
		this.picturePath = uri.toString();
	}

	/**
	 * Sets the bitmap for the image size
	 * @param theImageView
	 * @param theSize
	 * @param shouldCache
	 */
	private void setBitmapOnImageViewForSize(ImageView theImageView,
			int theSize, boolean shouldCache) {
		ImageLoadingFactory.loadBitmap(this, getPictureUri(), theImageView,
				theSize, shouldCache);
	}

	/**
	 * Sets the bitmap for the picture to the ImageView
	 * @param theImageView
	 */
	public void setLargeBitmapOnImageView(ImageView theImageView) {
		setBitmapOnImageViewForSize(theImageView, 1000, false);
	}

	/**
	 * Sets the bitmap for the picture to the imageview
	 * @param theImageView
	 */
	public void setSmallBitmapOnImageView(ImageView theImageView) {
		if (picture == null) {
			setBitmapOnImageViewForSize(theImageView, 160, true);
		} else {
			theImageView.setImageBitmap(picture);
		}
	}
}
