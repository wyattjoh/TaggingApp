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
import ca.ualberta.cs.taggingapp.models.ApplicationState;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

public class AddNameToTag extends Activity {

	EditText tagName;
	EditText tagURL;
	ListView tagList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_name_to_tag);

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
			regList.get(0).setTag(tag);
			tag.addTaggedRegion(regList.get(0));

			// Error tracking println
			System.out.println(tag.getName());
			System.out.println(tag.getURL());
			ArrayList<Region> regs = tag.getTaggedRegions();
			for (int i = 0; i < regs.size(); i++)
				System.out.println(regs.get(i).getLowerRightCorner().x);

			TagList.getInstance().addTag(tag);
			//PictureList.getInstance().updatePic(0, pic)
			
			ApplicationState.getInstance().save();
			
			AddNameToTag.this.finish(); //This probably needs to go to the ViewFullPic activity
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
