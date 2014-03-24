package ca.ualberta.cs.taggingapp.views;

import java.io.File;
import java.util.ArrayList;

//import ca.ualberta.cs.taggingapp.R;

//import taggingapp.views.ImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.widget.GridView;

public class CameraAndPhoto extends Activity {
	
	Uri imageFileUri;
	
	private ArrayList<String> imageUrls = new ArrayList<String>();
	private PictureAdapter myAdapter = new PictureAdapter(this);
	private GridView gridView;
	
	public static final int SHOW_PICTURES_IN_GALLERY = 1;
	public static final int TAKE_PICTURE = 2;
	public static final int RETURN_PICTURES = 3;
	
	
	/**
	 * Create a file where the camera will save the picture and start the
	 * camera.
	 */
	//public void takeAPhoto() {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_camera_and_photo_view);
		
		String folder = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/tmp";
		File folderF = new File(folder);

		// if file doesn't exist create a file
		if (!folderF.exists()) {
			folderF.mkdir();
		}

		// save file with the current time
		String imageFilePath = folder + "/" + System.currentTimeMillis()
				+ ".jpg";
		File imageFile = new File(imageFilePath);
		imageFileUri = Uri.fromFile(imageFile);

		// refresh
		sendBroadcast(new Intent(
				Intent.ACTION_MEDIA_MOUNTED,
				Uri.parse("file://" + Environment.getExternalStorageDirectory())));

		// intentC has information about image and is set to start the camera
		Intent intentC = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intentC.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);

		startActivityForResult(intentC, TAKE_PICTURE);
		finish();

	}
	

	/**
	 * Start an activity that selects a photo from the gallery.
	 */
	public void selectPhoto() {

		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		// returns the path of the photo selected from gallery
		startActivityForResult(intent, SHOW_PICTURES_IN_GALLERY);

	}
	
	/**
	 * Fill the grid view with the picture obtained from the gallery or from the
	 * camera.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode != RESULT_OK)
			return;

		if (requestCode == SHOW_PICTURES_IN_GALLERY) {

			Cursor cursor = getContentResolver().query(data.getData(),
					new String[] { MediaColumns.DATA }, null, null, null);
			if (!cursor.moveToFirst())
				return;

			String filePath = cursor.getString(cursor
					.getColumnIndex(MediaColumns.DATA));
			cursor.close();

			this.fillData(filePath);

		} 
			else if (requestCode == TAKE_PICTURE) {
			String path = imageFileUri.toString();
			this.fillData(path.replace("file://", ""));
		}

	}
	
	/**
	 * Add the file path to the list of URLs and add the corresponding photo to
	 * the grid view.
	 * 
	 * @param filePath
	 */

	private void fillData(String filePath) {
		this.imageUrls.add(filePath);
		this.myAdapter.addPhoto(BitmapFactory.decodeFile(filePath));
		this.gridView.setAdapter(myAdapter);
	}

}
