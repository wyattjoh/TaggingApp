package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.RegionController;
import ca.ualberta.cs.taggingapp.models.DrawImageView;
import ca.ualberta.cs.taggingapp.models.Picture;

public class AddTag extends Activity {

	Integer picId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		setTitle("Tagging App");

		Bundle extras = getIntent().getExtras();
		picId = (Integer) extras.get("imageName");

		ImageView img= (ImageView) findViewById(R.id.fullImage);
		img.setImageResource(picId);

		img.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					int x = (int) event.getX();
					int y = (int) event.getY();
					int coordinates[] = new int[2];
					v.getLocationOnScreen(coordinates);
					/* // Print for debugging
	            String s = "x-Coordinate: \n" + x + "\ny-Coordinate: \n" + y;
	    		Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
	            String t = "/nx-Coordinate: \n" + coordinates[0] + "\ny-Coordinate: \n" + coordinates[1];
	    		Toast.makeText(getBaseContext(), t, Toast.LENGTH_SHORT).show();
					 */
					int realX = x - coordinates[0];
					int realY = y - coordinates[1];
					String u = "x-Coordinate: \n" + realX + "\ny-Coordinate: \n" + realY;
					Toast.makeText(getBaseContext(), u, Toast.LENGTH_SHORT).show();
				}

				return true;
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_tag, menu);

		return true;
	}

	public boolean onTouchEvent(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:
		}
		String s = "x-Coordinate: \n" + x + "\ny-Coordinate: \n" + y;
		Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

		return false;
	}

	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}

	public boolean onTouch(View view, MotionEvent event) {

		// This code is broken//
//		DrawImageView drawview = (DrawImageView) view;
//
//		int eventType = event.getAction();
//		int touchX = 0;
//		int touchY = 0;
//		switch (eventType) {
//		case MotionEvent.ACTION_DOWN: 
//			// touch the screen: get coordinates of touch
//			touchX = (int) event.getX();
//			touchY = (int) event.getY();
//			break;
//
//		case MotionEvent.ACTION_MOVE:
//			// touch moves across the screen
//			break;
//		case MotionEvent.ACTION_UP:   
//			// touch lifts off the screen
//			break;
//		}
//
//		// draw the bounding box
//		drawview.invalidate();
//		drawview.drawRect = true;
//
//		Picture pic = new Picture();
//		// create new region
//		RegionController newRegion = new RegionController();
//		newRegion.createRegion(pic, touchX, touchY);
//
//		String s = "called";
//		Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
		//store in SQL database


		return true;
	}
}
