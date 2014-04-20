package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.RegionList;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

/**
 * @author Tagging Group Activity that displays the photo and the selected tag
 *         directly below it. It also displays the options to 'edit', 'delete',
 *         and view more images containing the tag.
 * 
 */
public class TagAndPhoto extends Activity {
	public final static String TAG_ID = "TAG_ID";

	private Picture thePicture;
	private Tag tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_and_photo);
		setTitle("TaggingApp");

		// Get the extras and set the proper image to the view
		Bundle extras = getIntent().getExtras();
		thePicture = PictureList.getInstance().getSelected();

		String tagId = extras.getString(TAG_ID);

		tag = TagList.getInstance().get(tagId);

		ImageView img = (ImageView) findViewById(R.id.picViewer);
		thePicture.setLargeBitmapOnImageView(img);

		// Append to the textview
		TextView tagName = (TextView) findViewById(R.id.tag);
		tagName.setText(tag.getName());

		TextView tagURL = (TextView) findViewById(R.id.tagURL);
		tagURL.setText(tag.getURL());

		Button moreImages = (Button) findViewById(R.id.morePhotosWTag);
		moreImages.append(tag.getName());
	}

	// OnClick for the 'more photos' button
	public void morePhotos(View view) {
		Intent i = new Intent(TagAndPhoto.this, TagRefinedImages.class);
		i.putExtra(TagRefinedImages.TAG_ID, tag.getId());
		startActivity(i);
	}

	// OnClick for the 'edit tag' button
	public void editTag(View view) {
		Intent i = new Intent(TagAndPhoto.this, EditTag.class);
		i.putExtra(EditTag.TAG_ID, tag.getId());
		startActivity(i);
	}

	// OnClick for the 'delete tag' button
	public void deleteTag(View view) {
		// Change this code
		RegionList.getInstance().deleteAllRegionsForTag(tag);
		TagList.getInstance().remove(tag);
		finish();
	}

	@Override
	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}

}
