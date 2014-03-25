package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.DrawImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;

public class AddTag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		setTitle("Tagging App");
		Picture thePicture = PictureList.getInstance().getSelected();
		DrawImageView picture = (DrawImageView) findViewById(R.id.drawImageView1);
		picture.setBackground(new BitmapDrawable(getResources(), thePicture.getPicture()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//		getMenuInflater().inflate(R.menu.add_tag, menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_tag_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.accept:
			
			return true;
		case R.id.decline:
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}
}
