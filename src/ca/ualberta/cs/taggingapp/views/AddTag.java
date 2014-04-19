package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.Logger;
import ca.ualberta.cs.taggingapp.models.ActiveUserModel;
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
	private Matrix matrix = new Matrix();
	private float scale = 1f;
	private Point zoomCenter = new Point(0, 0);
	private ScaleGestureDetector SGD;
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
		picture.setPicture(thePicture);

		SGD = new ScaleGestureDetector(this, new ScaleListener());

		picture.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

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
	public boolean onTouchEvent(MotionEvent ev) {
		Log.w("AddTag", ActiveUserModel.getShared().getUser()
				.getBoundingBoxSetting());
		if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "ZOOM") {

			SGD.onTouchEvent(ev);
			zoomCenter.set(Math.round(SGD.getFocusX()),
					Math.round(SGD.getFocusY()));
		}
		return true;
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
		String boundingBoxSetting = ActiveUserModel.getShared().getUser()
				.getBoundingBoxSetting();

		if (boundingBoxSetting == "DRAG" || boundingBoxSetting == "DEFAULT_TAP") {
			region = new Region(pic, picture.getUpperLeftPoint(),
					picture.getLowerRightPoint());
		} else {
			region = new Region(pic, zoomCenter);
			region.setHeight(Math.round(picture.getHeight() * scale));
			region.setWidth(Math.round(picture.getWidth() * scale));
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

	private class ScaleListener extends SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			scale = Math.max(0.1f, Math.min(scale, 5.0f));
			matrix.setScale(scale, scale);
			picture.setImageMatrix(matrix);
			return true;
		}
	}
}
