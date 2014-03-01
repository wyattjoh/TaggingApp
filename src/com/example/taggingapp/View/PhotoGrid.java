package com.example.taggingapp.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

import com.example.taggingapp.R;

public class PhotoGrid extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_photo_grid);
		
		setContentView(R.layout.grid_layout);
		 
        GridView gridView = (GridView) findViewById(R.id.photo_grid_view);
 
        // Instance of ImageAdapter Class
        gridView.setAdapter(new GridImageAdapter(this));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_grid, menu);
		return true;
	}

}
