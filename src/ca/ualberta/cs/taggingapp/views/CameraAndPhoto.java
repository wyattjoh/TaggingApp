package ca.ualberta.cs.taggingapp.views;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import ca.ualberta.cs.taggingapp.R;
import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * @author Tagging Gtroup Activity that handles taking and saving a photo
 * 
 */
public class CameraAndPhoto extends Activity {
	Uri mCurrentPhotoUri;
	String mCurrentPhotoPath;

	static final int REQUEST_TAKE_PHOTO = 1;
	static final String PHOTO_URI = "PHOTO_URI";

	/**
	 * Create a file where the camera will save the picture and start the
	 * camera.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("TaggingApp");

		dispatchTakePictureIntent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_TAKE_PHOTO) {

			if (resultCode == RESULT_OK) {
				// Add the pic to the gallery
				galleryAddPic();

				Intent returnIntent = new Intent();
				returnIntent.putExtra(PHOTO_URI, mCurrentPhotoUri.toString());
				setResult(RESULT_OK, returnIntent);
				finish();
			} else if (resultCode == RESULT_CANCELED) {
				finish();
			}
		}
	}

	/**
	 * @return ImageFile
	 * @throws IOException
	 */
	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();

		// Save the Uri
		mCurrentPhotoUri = Uri.fromFile(image);

		return image;
	}

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}
	}

	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, mCurrentPhotoUri);
		getApplicationContext().sendBroadcast(mediaScanIntent);
	}
}
