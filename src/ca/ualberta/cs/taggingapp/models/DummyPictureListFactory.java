/**
 * 
 */
package ca.ualberta.cs.taggingapp.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import ca.ualberta.cs.taggingapp.R;

/**
 * @author Wyatt Johnson
 *
 */
public class DummyPictureListFactory {
	public static void createDummyPictures(Context context) {
	    // Keep all Images in array
	    Integer[] picIDs = {
	            R.drawable.ex_pic1,
	            R.drawable.ex_pic2,
	            R.drawable.ex_pic5,
	            R.drawable.ex_pic2,
	            R.drawable.ex_pic3
	    };
	    
	    PictureList thePictureList = PictureList.getInstance();
	    
	    for (Integer pictureId : picIDs) {
			Picture thePicture = new Picture();
			Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
					pictureId);
			thePicture.setPicture(icon);
			
			Log.w("DummyPictureListFactory", "Created Picture");
			
			thePictureList.addPicture(thePicture);
			
			Log.w("DummyPictureListFactory", "Added Picture");
		}
	}
}
