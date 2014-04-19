package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;
import java.util.Collections;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

/**
 * @author Tagging Gtroup
 * Activity that controls the text input screen for adding names to 
 * new bounding boxes. After a bounding box is selected from the previous
 * screen (AddTag), the user is brought to this screen to assign a new 
 * name and URL to the region. 
 *
 */

public class AddNameToTag extends Activity {

	// The input boxes present in the screen
	EditText tagName;
	EditText tagURL;
	ListView tagList;
	// The adapter for the tag list that appears below the URL box
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_name_to_tag);
		setTitle("TaggingApp");

		// Get and assign the textviews so that the values can be retrieved
		tagName = (EditText) this.findViewById(R.id.tag_name);
		tagURL = (EditText) this.findViewById(R.id.tag_url);
		// Get and assign the listview
		tagList = (ListView) this.findViewById(R.id.tags_list_view);
		// Init the arraylist to hold the tags for the listview
		final ArrayList<String> tags = new ArrayList<String>();
		for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
			tags.add(TagList.getInstance().getTags().get(i).getName());
		}
		// Init the adapter to the proper tags list
		adapter = new ArrayAdapter<String>(this.getBaseContext(),
				R.layout.list_item, tags);
		// Link the adapter to the listview
		tagList.setAdapter(adapter);

		// If a listitem is tapped...
		tagList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				tagName.setText("");
				adapter.getItem(position);
				tagName.append(adapter.getItem(position));
				
				// Sets the proper URL and TAG name from the listview to the input boxes
				for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
					if (TagList.getInstance().getTags().get(i).getName()
							.equals(adapter.getItem(position))) {
						tagURL.setText("");
						tagURL.append(TagList.getInstance().getTags().get(i)
								.getURL());
					}
				}

			}
		});

		// The following block handles the textchangedlistener, which will refine the
		// listview according to what is typed into the tagName field
		tagName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_tag_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Handles click events for the menu items (checkmark and cross)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.accept:
			addTextTag();
			AddNameToTag.this.finish(); 
			return true;
		case R.id.decline:
			AddNameToTag.this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Upon selecting the checkmark, this code is run before leaving the activity
	// so that the new tag and URL are saved
	protected void addTextTag() {
		// Create tag and region and save it here
		Picture pic = PictureList.getInstance().getSelected();
		// Get the region from the previous activity
		Region region = AddTag.getRegion();
		pic.addRegion(region);
		
		// Create the new tag, get the region list, and get the most recently
		// created tag
		Tag tag = new Tag(tagName.getText().toString(), tagURL.getText()
				.toString());
		ArrayList<Region> regList = PictureList.getInstance().getSelected()
				.getRegions();
		Collections.reverse(regList);

		// Check if the same tag already exists. If so, simply add the bounding box region
		// to the instance of the already created tag. If not, create the new tag and add
		// it to the tagList.
		
		// Check for duplicate
		boolean found = false;
		Tag dupTag = null;
		for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
			if (TagList.getInstance().getTags().get(i).getName()
					.equals(tag.getName())
					&& TagList.getInstance().getTags().get(i).getURL()
							.equals(tag.getURL())) {
				found = true;
				dupTag = TagList.getInstance().getTags().get(i);
			}
		}
		// If the tag exists:
		if (found) {
			dupTag.addTaggedRegion(regList.get(0));
			regList.get(0).setTag(dupTag);
		} else {
			// If not:
			tag.addTaggedRegion(regList.get(0));
			regList.get(0).setTag(tag);
			TagList.getInstance().addTag(tag);
		}

		// Error tracking code. You can delete this block if needed.
		System.out.println(tag.getName());
		System.out.println(tag.getURL());
		ArrayList<Region> regs = tag.getTaggedRegions();
		for (int i = 0; i < regs.size(); i++)
			System.out.println(regs.get(i).getLowerRightCorner().x);

		// Save the new instances onto the SD card
		TagList.getInstance().save();
		PictureList.getInstance().save();
	}

	// In the case that the user decided to leave this screen without entering a proper
	// name and URL, the last region must be deleted from the regionList, because the 
	// name and URL fields will be null. This block essentially cancels the add tag action
	// and cleans up the instances that are not fully initialized.
	@Override
	public void onPause() {
		super.onPause();
		ArrayList<Region> regList = PictureList.getInstance().getSelected()
				.getRegions();
		Collections.reverse(regList);
		if (regList.get(0).getTag().getName() == null) {
			regList.remove(0);
			PictureList.getInstance().save();
		}

	}

}
