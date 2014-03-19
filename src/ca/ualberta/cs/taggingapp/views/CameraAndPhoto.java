package ca.ualberta.cs.taggingapp.views;

import java.io.File;
import java.util.ArrayList;

//import taggingapp.views.ImageAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class CameraAndPhoto extends Activity {
	
	Uri imageFileUri;
	
	private ArrayList<String> imageUrls = new ArrayList<String>();
	private ImageAdapter myAdapter = new ImageAdapter(this);
	private GridView gridView;
	
	/**
	 * Fill the grid view with the picture obtained from the gallery or from the
	 * camera.
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode != RESULT_OK)
			return;

		//if (requestCode == PICK_PICTURE_FROM_GALLERY) {

			Cursor cursor = getContentResolver().query(data.getData(),
					new String[] { Media.DATA }, null, null, null);
			if (!cursor.moveToFirst())
				return;

			String filePath = cursor.getString(cursor
					.getColumnIndex(Media.DATA));
			cursor.close();

			this.fillData(filePath);

		//} 
			//else if (requestCode == TAKE_PICTURE) {
			String path = imageFileUri.toString();
			this.fillData(path.replace("file://", ""));
		//}

	}
	
	/**
	 * Add the file path to the list of URLs and add the corresponding photo to
	 * the grid view.
	 * 
	 * @param filePath
	 */

	private void fillData(String filePath) {
		// TODO Auto-generated method stub
		this.imageUrls.add(filePath);
		this.myAdapter.addPhoto(BitmapFactory.decodeFile(filePath));
		this.gridView.setAdapter(myAdapter);
	}

}
