package ca.ualberta.cs.taggingapp.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;

/**
 * @author Tagging Group
 * Activity that displays the photo and the selected tag directly below it.
 * It also displays the options to 'edit', 'delete', and view more images
 * containing the tag.
 *
 */
public class TagAndPhoto extends Activity {
	Picture thePicture;
	String tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_and_photo);
		setTitle("TaggingApp");

		// Get the extras and set the proper image to the view
		Bundle extras = getIntent().getExtras();
		thePicture = PictureList.getInstance().getSelected();
		// Get the tag name
		tag = extras.getString("tagName");

		ImageView img = (ImageView) findViewById(R.id.picViewer);
		try {
			img.setImageBitmap(thePicture.getPicture());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Append to the textview
		TextView tagName = (TextView) findViewById(R.id.tag);
		tagName.append(tag);
		// Append to the textview
		TextView tagURL = (TextView) findViewById(R.id.tagURL);
		// Get all of the regions associated with that tag
		ArrayList<Region> regs = PictureList.getInstance().getSelected()
				.getRegions();
		for (int i = 0; i < regs.size(); i++) {
			if (regs.get(i).getTag().getName().equals(tag)) {
				tagURL.append(regs.get(i).getTag().getURL());
			}
		}

		Button moreImages = (Button) findViewById(R.id.morePhotosWTag);
		moreImages.append(tag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_and_photo, menu);
		return true;
	}

	// OnClick for the 'more photos' button
	public void morePhotos(View view) {
		Intent i = new Intent(TagAndPhoto.this, TagRefinedImages.class);
		i.putExtra("tagName", tag);
		startActivity(i);
		TagAndPhoto.this.finish();
	}
	// OnClick for the 'edit tag' button
	public void editTag(View view) {
		Intent i = new Intent(TagAndPhoto.this, EditTag.class);
		i.putExtra("tagName", tag);
		startActivity(i);
		TagAndPhoto.this.finish();
	}
	// OnClick for the 'delete tag' button
	public void deleteTag(View view) {
		// Change this code
		ArrayList<Region> regs = thePicture.getRegions();
		for (int i = 0; i < regs.size(); i++) {
			if (regs.get(i).getTag().getName().equals(tag)) {
				PictureList.getInstance().getSelected()
						.removeRegion(regs.get(i));
			}
		}
		PictureList.getInstance().save();
		TagAndPhoto.this.finish();
	}

	@Override
	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}

}
