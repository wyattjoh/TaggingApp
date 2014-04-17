package ca.ualberta.cs.taggingapp.views;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.Logger;
import ca.ualberta.cs.taggingapp.models.ActiveUserModel;
import ca.ualberta.cs.taggingapp.models.DrawImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;

public class AddTag extends Activity implements OnNavigationListener {

	DrawImageView picture;
	int tagType = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		setTitle("Tagging App");
		Picture thePicture = PictureList.getInstance().getSelected();
		picture = (DrawImageView) findViewById(R.id.drawImageView1);

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

		// Drop down menu
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		SpinnerAdapter spinner = ArrayAdapter.createFromResource(this, R.array.action_list, R.layout.spinner_item);
		OnNavigationListener navigationListener = new OnNavigationListener() {
			  @Override
			  public boolean onNavigationItemSelected(int position, long itemId) {
			    switch (position) {
			    case 1:
			    	ActiveUserModel.getShared().getUser().setBoundingBoxSetting("ZOOM");
			    	return true;
			    case 2:
			    	ActiveUserModel.getShared().getUser().setBoundingBoxSetting("DRAG");
			    	return true;
			    case 3:
			    	ActiveUserModel.getShared().getUser().setBoundingBoxSetting("DEFAULT_TAP");
			    	return true;
			    default:
			    	return true;
			    }
			  }
			};
		actionBar.setListNavigationCallbacks(spinner, navigationListener);
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		Toast.makeText(getBaseContext(), "postion" + position,
				Toast.LENGTH_LONG).show();
		if (tagType == position) {
			tagType = position;
			Logger.start("user", tagType);
		} else {
			Logger.event("Invalid test");
			Logger.end();
			Logger.start("user", tagType);
		}

		return false;
		// Our logic
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.add_tag, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_tag_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

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

	protected void addRegion() {
		Picture pic = PictureList.getInstance().getSelected();
		Region region = new Region(pic, picture.getUpperLeftPoint(),
				picture.getLowerRightPoint());
		pic.addRegion(region);
	}

	@Override
	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}
}
