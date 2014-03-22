/**
 * 
 */
package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
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
			Bitmap icon = ResizedBitmapFactory.decodeSampledBitmapFromResource(context.getResources(), pictureId, 100, 100);
			thePicture.setPicture(icon);

			thePictureListPrimative.add(thePicture);
		}
		
		thePictureList.setPictureList(thePictureListPrimative);
	}
}
