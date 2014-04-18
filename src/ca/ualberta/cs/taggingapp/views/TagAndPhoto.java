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
import ca.ualberta.cs.taggingapp.models.TagList;

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

		TextView tagName = (TextView) findViewById(R.id.tag);
		tagName.append(tag);

		TextView tagURL = (TextView) findViewById(R.id.tagURL);

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
