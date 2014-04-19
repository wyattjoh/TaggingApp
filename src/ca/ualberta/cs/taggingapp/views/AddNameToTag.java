package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.RegionList;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

/**
 * @author Tagging Gtroup Activity that controls the text input screen for
 *         adding names to new bounding boxes. After a bounding box is selected
 *         from the previous screen (AddTag), the user is brought to this screen
 *         to assign a new name and URL to the region.
 * 
 */

public class AddNameToTag extends Activity {

	EditText tagNameField;
	EditText tagUrlField;
	ListView tagList;
	TagArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_name_to_tag);
		setTitle("TaggingApp");

		tagNameField = (EditText) this.findViewById(R.id.tag_name);
		tagUrlField = (EditText) this.findViewById(R.id.tag_url);
		tagList = (ListView) this.findViewById(R.id.tags_list_view);

		adapter = new TagArrayAdapter(getApplicationContext(), TagList
				.getInstance().getArrayList());

		tagList.setAdapter(adapter);

		// If a listitem is tapped...
		tagList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Tag tag = adapter.getItem(position);
				Toast.makeText(getApplicationContext(), "Tag: " + tag.getId(),
						Toast.LENGTH_LONG).show();

				finishTaggingAction(tag);

				// tagName.setText("");
				// adapter.getItem(position);
				// tagName.append(adapter.getItem(position));
				//
				// for (int i = 0; i < TagList.getInstance().getTags().size();
				// i++) {
				// if (TagList.getInstance().getTags().get(i).getName()
				// .equals(adapter.getItem(position))) {
				// tagURL.setText("");
				// tagURL.append(TagList.getInstance().getTags().get(i)
				// .getURL());
				// }
				// }
			}
		});

		tagNameField.addTextChangedListener(new TextWatcher() {

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
			finishTaggingAction(null);
			return true;
		case R.id.decline:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void finishTaggingAction(Tag tag) {
		addTextTag(tag);
		finish();
	}

	protected void addTextTag(Tag tag) {
		// Get the current picture
		Picture pic = PictureList.getInstance().getSelected();

		// Get the region from the previous activity
		Region region = AddTag.getRegion();

		final Boolean isNewPicture = tag == null;

		if (isNewPicture) {
			String tagName = tagNameField.getText().toString();
			String tagUrl = tagUrlField.getText().toString();

			tag = new Tag(tagName, tagUrl);
		}

		// Add the region to the tag
		tag.addRegion(region);

		// Set the tag, pic already set on construction
		region.setTag(tag);

		// Adds the region to the list
		RegionList.getInstance().add(region);

		// Add the region to the picture
		pic.addRegion(region);

		// Save the picture list
		PictureList.getInstance().save();

		// Save the tag
		if (isNewPicture) {
			TagList.getInstance().add(tag);
		} else {
			TagList.getInstance().save();
		}
	}
}
