/**
 * 
 */
package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ca.ualberta.cs.taggingapp.R;

/**
 * @author Wyatt Johnson
 * 
 */
public class DummyPictureListFactory {
	public static void createDummyPictures(Context context) {
		// Keep all Images in array
		Integer[] picIDs = { R.drawable.ex_pic1, R.drawable.ex_pic2,
				R.drawable.ex_pic5, R.drawable.ex_pic2, R.drawable.ex_pic3 };

		ArrayList<Picture> thePictureListPrimative = new ArrayList<Picture>();
		PictureList thePictureList = PictureList.getInstance();

		for (Integer pictureId : picIDs) {
			Picture thePicture = new Picture();
			Bitmap icon = decodeSampledBitmapFromResource(context.getResources(), pictureId, 100, 100);
			thePicture.setPicture(icon);

			thePictureListPrimative.add(thePicture);
		}
		
		thePictureList.setPictureList(thePictureListPrimative);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}
}
