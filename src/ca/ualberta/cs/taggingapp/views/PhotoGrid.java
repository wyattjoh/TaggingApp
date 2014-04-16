package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

public class PhotoGrid extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_photo_grid);

		setContentView(R.layout.grid_layout);

		GridView gridView = (GridView) findViewById(R.id.photo_grid_view);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new GridImageAdapter(this,null));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_grid, menu);
		return true;
	}

}
