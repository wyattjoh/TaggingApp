package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

public class AddNameToTag extends Activity {

	EditText tagName;
	EditText tagURL;
	ListView tagList;
	
	Region region;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_name_to_tag);
		
		Picture pic = PictureList.getInstance().getSelected();
		tagName = (EditText) this.findViewById(R.id.tag_name);
		tagURL = (EditText) this.findViewById(R.id.tag_url);
		tagList = (ListView) this.findViewById(R.id.tags_list_view);
		
		//Point topLeft = (Point) extras.get("upperLeft");
		//Point bottomRight = (Point) extras.get("lowerRight");
		//Region region  = new Region(pic, (int)topLeft.x, (int)topLeft.y, (int)bottomRight.x, (int)bottomRight.y);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_tag_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.accept:
			// Create tag and region and hopefully save it here
			Tag tag = new Tag(tagName.getText().toString(), tagURL.getText().toString());
			ArrayList <Region> regList = PictureList.getInstance().getSelected().getRegions();
			Collections.reverse(regList);
			tag.addTaggedRegion(regList.get(0));
			TagList.getInstance().addTag(tag);
			AddNameToTag.this.finish();
			return true;
		case R.id.decline:
			//Intent j = new Intent(AddNameToTag.this, ViewFullPic.class);
			//startActivity(j);
			AddNameToTag.this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
