package ca.ualberta.cs.taggingapp.views;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.Logger;
import ca.ualberta.cs.taggingapp.models.ActiveUserModel;
import ca.ualberta.cs.taggingapp.models.DrawImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;

/**
 * @author Tagging Group
 * Activity that allows the user to draw bounding boxes on the image.
 *
 */
public class AddTag extends Activity {
	
	DrawImageView picture;
	int tagType = 0;
	// The names and corresponding tag types the user can choose from
	String[] tagMethods = { "Zoom", "Drag", "Double Tap" };
	String[] tagMethodKeys = { "ZOOM", "DRAG", "DEFAULT_TAP" };

	private static Region region = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		setTitle("TaggingApp");
		// Get the correct image form the PictureList and set the ImageView
		Picture thePicture = PictureList.getInstance().getSelected();
		picture = (DrawImageView) findViewById(R.id.drawImageView);

		try {
			picture.setBackground(new BitmapDrawable(getResources(), thePicture
					.getPicture()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create an alert to prompt the user to enter the tagging method that they
		// want to use to tag the photo
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Tagging Method")
		.setItems(tagMethods, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Logger.start(ActiveUserModel.getShared().getUser()
						.getEmail(), tagMethods[which]);

				ActiveUserModel.getShared().getUser()
				.setBoundingBoxSetting(tagMethodKeys[which]);
				Toast.makeText(getBaseContext(),
						tagMethods[which] + " tagging selected",
						Toast.LENGTH_LONG).show();
			}
		})
		// Prevents cancel of dialog box
		.setCancelable(false);

		builder.create().show();
	}

	// Simply sets the correct menu bar for the window
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.add_tag, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_tag_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Handles the onClick events for the checkmark and cross buttons in the action bar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.accept:
			addRegion();
			Intent i = new Intent(AddTag.this, AddNameToTag.class);
			startActivity(i);
			finish();
			Logger.end();
			return true;
		case R.id.decline:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Handles getting the region data and creating the new region instance
	protected void addRegion() {
		Picture pic = PictureList.getInstance().getSelected();
		if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DRAG" ||
				ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DEFAULT_TAP") {
			region = new Region(pic, picture.getUpperLeftPoint(),
					picture.getLowerRightPoint());
		} else {
			region = new Region(pic, picture.getZoomCenter());
			region.setHeight(Math.round(picture.getHeight() * picture.getScale()));
			region.setWidth(Math.round(picture.getWidth() * picture.getScale()));
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}

	/**
	 * @return the region
	 */
	public static Region getRegion() {
		return region;
	}
}
