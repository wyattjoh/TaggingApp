package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

/**
 * @author Tagging Group
 * The gridview that appears in the GridViewFragment and the 
 * TagRefinedImages activities. Sets the proper adapter also.
 *
 */
public class PhotoGrid extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		setTitle("TaggingApp");

		GridView gridView = (GridView) findViewById(R.id.photo_grid_view);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new GridImageAdapter(this, null));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_grid, menu);
		return true;
	}

}
