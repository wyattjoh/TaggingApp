package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.ActiveUserModel;
import ca.ualberta.cs.taggingapp.models.BoundingBoxSetting;
import ca.ualberta.cs.taggingapp.models.DrawImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;

/**
 * @author Tagging Group Activity that allows the user to draw bounding boxes on
 *         the image.
 * 
 */
public class AddTag extends Activity {
	DrawImageView drawImageView;
	private static Region region = null;
	private Toast theWarning = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		setTitle("TaggingApp");
		// Get the correct image form the PictureList and set the ImageView
		Picture thePicture = PictureList.getInstance().getSelected();
		drawImageView = (DrawImageView) findViewById(R.id.drawImageView);
		drawImageView.setPicture(thePicture);
		region = null;

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Tagging Method")
				.setItems(BoundingBoxSetting.getNames(),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ActiveUserModel
										.getShared()
										.getUser()
										.setBoundingBoxSetting(
												BoundingBoxSetting
														.getFromIndex(which));
							}
						})
				// Prevents cancel of dialog box
				.setCancelable(false);

		builder.create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.add_tag, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_tag_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Handles the onClick events for the checkmark and cross buttons in the
	// action bar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.accept:
			if (addRegion()) {
				Intent i = new Intent(AddTag.this, AddNameToTag.class);
				startActivity(i);
				finish();
			} else {
				openToast("No region selected!");
			}
			return true;
		case R.id.decline:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Handles getting the region data and creating the new region instance
	protected boolean addRegion() {
		Picture pic = PictureList.getInstance().getSelected();

		Point upperLeftPoint = drawImageView.getUpperLeftPoint();
		Point lowerRightPoint = drawImageView.getLowerRightPoint();

		if (upperLeftPoint != null && lowerRightPoint != null) {
			region = new Region(pic, upperLeftPoint, lowerRightPoint);
			return true;
		} else {
			return false;
		}
	}

	private void openToast(String theNotification) {
		if (theWarning != null) {
			theWarning.cancel();
			theWarning.setText(theNotification);
		} else {
			theWarning = Toast.makeText(getApplicationContext(),
					theNotification, Toast.LENGTH_LONG);
		}

		theWarning.show();
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
