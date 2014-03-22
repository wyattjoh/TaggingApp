package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.RegionController;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;

public class TagAndPhoto extends Activity {
	Picture thePicture;
	String tag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_and_photo);
		setTitle("Tagging App");
		
		Bundle extras = getIntent().getExtras();
		thePicture = PictureList.getInstance().getSelected();
		
		tag = extras.getString("tagName");
		
		ImageView img= (ImageView) findViewById(R.id.picViewer);
		img.setImageBitmap(thePicture.getPicture());
		
		TextView tagName = (TextView) findViewById(R.id.tag);
		tagName.append(tag);
		
		TextView tagURL = (TextView) findViewById(R.id.tagURL);
		tagURL.append("https://stackoverflow.com");
		
		Button moreImages = (Button) findViewById(R.id.morePhotosWTag);
		moreImages.append(tag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_and_photo, menu);
		return true;
	}
	
	public void morePhotos(View view) {
		Intent i = new Intent(TagAndPhoto.this, TagRefinedImages.class);
		i.putExtra("tagName", tag);
		startActivity(i);
		TagAndPhoto.this.finish();
	}
	
	public void editTag(View view) {
		Intent i = new Intent(TagAndPhoto.this, EditTag.class);
		i.putExtra("tagName", tag);
		startActivity(i);
		TagAndPhoto.this.finish();
	}
	
	public void deleteTag(View view) {
		RegionController rc = new RegionController();
		//rc.removeRegion();
	}
	
	@Override
	public void onPause() {
	     super.onPause();
	     overridePendingTransition(0, 0);
	 }

}
