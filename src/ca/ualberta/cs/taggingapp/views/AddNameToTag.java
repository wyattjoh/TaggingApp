package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.TagListController;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;

public class AddNameToTag extends Activity {

	EditText tagName;
	EditText tagURL;
	ListView tagList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_name_to_tag);
		
		Picture pic = PictureList.getInstance().getSelected();
		tagName = (EditText) this.findViewById(R.id.tag_name);
		tagURL = (EditText) this.findViewById(R.id.tag_url);
		tagList = (ListView) this.findViewById(R.id.tags_list_view);
		
		//Bundle extras = getIntent().getExtras();
		//Point topLeft = (Point) extras.get("upperLeft");
		//Point bottomRight = (Point) extras.get("lowerRight");
		
		//Region region  = new Region(pic, (int)topLeft.x, (int)topLeft.y, (int)bottomRight.x, (int)bottomRight.y);
		
		//TagListController tlc = new TagListController();
		//Tag tag = tlc.addNewTag(region, tagName.getText().toString(), tagURL.getText().toString());
		
		//region.editRegionTag(tag);
		
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
				
			return true;
		case R.id.decline:
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
