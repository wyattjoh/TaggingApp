package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.RegionList;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;
import ca.ualberta.cs.taggingapp.models.TaggedImageView;

/**
 * @author Tagging Group This activity shows the full photo, and all of the tags
 *         in a list view below it. The user is taken here after they select a
 *         image from the grid image view in swipe super.
 * 
 */
public class ViewFullPic extends Activity {

	TagArrayAdapter adapter;
	ListView miniTagsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_full_pic);
		setTitle("TaggingApp");

		setupListView();
	}

	protected void setupListView() {
		// Get the listview from the XML file
		miniTagsList = (ListView) this.findViewById(R.id.miniTagsList);

		LayoutInflater inflater = getLayoutInflater();
		ViewGroup header = (ViewGroup) inflater.inflate(
				R.layout.view_full_pic_header, miniTagsList, false);
		miniTagsList.addHeaderView(header, null, false);
		// Set the proper photo to the imageview
		Picture thePicture = PictureList.getInstance().getSelected();
		TaggedImageView picture = (TaggedImageView) header
				.findViewById(R.id.taggedImageView);
		picture.setPicture(thePicture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_full_pic_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.add:
			Intent intent = new Intent(this, AddTag.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
			return true;
		case R.id.discard:
			Picture thePicture = PictureList.getInstance().getSelected();

			// Remove all the regions
			RegionList.getInstance().deleteAllRegionsForPicture(thePicture);

			// Remove the picture
			PictureList.getInstance().remove(thePicture);

			// Delete all orphaned tags
			TagList.getInstance().deleteAllOrphanedTags();

			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		populateView();
	}

	protected void populateView() {
		Picture thePic = PictureList.getInstance().getSelected();

		ArrayList<Tag> theTagList = RegionList.getInstance()
				.getAllTagsFromPicture(thePic);

		adapter = new TagArrayAdapter(getApplicationContext(), theTagList, true);

		miniTagsList.setAdapter(adapter);

		miniTagsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Tag tag = adapter.getItem(position - 1);

				Intent i = new Intent(ViewFullPic.this, TagAndPhoto.class);

				i.putExtra(TagAndPhoto.TAG_ID, tag.getId());

				i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

				startActivity(i);
			}
		});
	}
}
