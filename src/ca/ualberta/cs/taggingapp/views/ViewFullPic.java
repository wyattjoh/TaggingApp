package ca.ualberta.cs.taggingapp.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.TaggedImageView;

public class ViewFullPic extends Activity {

	ArrayAdapter<String> adapter;
	ArrayList<String> s;
	ListView miniTagsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_full_pic);
		setTitle("Tagging App");

		Picture thePicture = PictureList.getInstance().getSelected();
		TaggedImageView picture = (TaggedImageView) findViewById(R.id.taggedImageView1);
		picture.setPicture(thePicture);
		try {
			picture.setBackground(new BitmapDrawable(getResources(), thePicture
					.getPicture()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		miniTagsList = (ListView) this.findViewById(R.id.miniTagsList);

		ArrayList<Region> regs = PictureList.getInstance().getSelected()
				.getRegions();
		s = new ArrayList<String>();
		s.add("NEW TAG");
		for (int i = 0; i < regs.size(); i++) {
			s.add(regs.get(i).getTag().getName());
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
				ViewFullPic.this.finish();
			}
		});
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
			Intent i = new Intent(ViewFullPic.this, AddTag.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(i);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		// ApplicationState.getInstance().load();
		ArrayList<Region> regs = PictureList.getInstance().getSelected()
				.getRegions();
		s = new ArrayList<String>();
		s.add("NEW TAG");
		System.out.println("SIZE: " + regs.size());
		for (int i = 0; i < regs.size(); i++) {
			s.add(regs.get(i).getTag().getName());
		}
		System.out.println("SIZE OF S: " + s.size());
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_item, s);
		miniTagsList.setAdapter(adapter);
	}
}
