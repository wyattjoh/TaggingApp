package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TaggedImageView;

public class ViewFullPic extends Activity {

	ArrayAdapter<String> adapter;
	ArrayList<String> s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_full_pic);
		setTitle("Tagging App");
		
		// Populate the view
		populateView();
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
			if (thePicture.getRegions().isEmpty()) {
				for (Region region : thePicture.getRegions()) {
					region.removeRegion();
				}
			}
			PictureList.getInstance().getPictureList().remove(thePicture);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	protected void populateView() {
		ListView miniTagsList = (ListView) this.findViewById(R.id.miniTagsList);
		
		ArrayList<Region> regs = PictureList.getInstance().getSelected()
				.getRegions();
		s = new ArrayList<String>();

		LayoutInflater inflater = getLayoutInflater();
		ViewGroup header = (ViewGroup)inflater.inflate(R.layout.view_full_pic_header, miniTagsList, false);
		miniTagsList.removeAllViewsInLayout();
		miniTagsList.addHeaderView(header, null, false);
		
		Picture thePicture = PictureList.getInstance().getSelected();
		TaggedImageView picture = (TaggedImageView) header.findViewById(R.id.taggedImageView);
		picture.setPicture(thePicture);
		
		Log.w("ViewFullPic",
				"Number of regions: " + Integer.toString(regs.size()));
		for (Region region : regs) {
			Tag theTag = region.getTag();

			s.add(theTag.getName());

			Log.w("ViewFullPic", "The tag: " + theTag.getName());
		}

		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_item, s);

		miniTagsList.setAdapter(adapter);

		miniTagsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(ViewFullPic.this, TagAndPhoto.class);
				i.putExtra("tagName", adapter.getItem(position));
				i.putExtra("pos", position);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(i);
				//finish();
			}
		});
	}
}
