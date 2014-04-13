package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.PictureList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class TagRefinedImages extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tag_refined_images);
		setTitle("Tagging App");

		Bundle extras = getIntent().getExtras();
		String tag = extras.getString("tagName");

		TextView tagName = (TextView) findViewById(R.id.tag);
		tagName.append(tag);

		TextView tagURL = (TextView) findViewById(R.id.tagURL);
		tagURL.append("https://stackoverflow.com");

		GridView gridView = (GridView) this
				.findViewById(R.id.refinedImgGridView);

		// Instance of ImageAdapter Class
		final GridImageAdapter gia = new GridImageAdapter(this);
		gridView.setAdapter(gia);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(TagRefinedImages.this, ViewFullPic.class);

				Integer imagePosition = (Integer) v.getTag();

				PictureList.getInstance().setSelected(imagePosition);

				startActivity(i);
				/*
				 * Toast.makeText(rootView.getContext(), "pic" +
				 * (gia.getItem(position)) +" selected",
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tag_refined_images, menu);
		return true;
	}

}
