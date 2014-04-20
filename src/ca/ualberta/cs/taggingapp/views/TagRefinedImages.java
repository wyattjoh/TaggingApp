package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

/**
 * @author Tagging Group Activity that displays photos with the same tag in a
 *         gridview, similar to the GridViewFragment class.
 * 
 */
public class TagRefinedImages extends Activity {
	public static final String TAG_ID = "TAG_ID";

	private Tag theTag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_refined_images);
		setTitle("TaggingApp");

		Bundle extras = getIntent().getExtras();

		String tagId = extras.getString(TAG_ID);

		theTag = TagList.getInstance().get(tagId);

		// Get the tagName and display it in the textview
		TextView tagName = (TextView) findViewById(R.id.tag);
		tagName.setText(theTag.getName());

		TextView tagURL = (TextView) findViewById(R.id.tagURL);
		tagURL.setText(theTag.getURL());

		GridView gridView = (GridView) this
				.findViewById(R.id.refinedImgGridView);

		GridImageAdapter gia = new GridImageAdapter(getApplicationContext(),
				theTag);

		gridView.setAdapter(gia);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(TagRefinedImages.this, ViewFullPic.class);

				Integer imagePosition = (Integer) v.getTag();

				PictureList.getInstance().setSelected(imagePosition);

				startActivity(i);
			}
		});
	}

}
