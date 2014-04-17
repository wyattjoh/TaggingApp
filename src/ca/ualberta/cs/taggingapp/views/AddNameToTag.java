package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
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

public class AddNameToTag extends Activity {

	EditText tagName;
	EditText tagURL;
	ListView tagList;
	ArrayAdapter <String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_name_to_tag);

		tagName = (EditText) this.findViewById(R.id.tag_name);
		tagURL = (EditText) this.findViewById(R.id.tag_url);
		tagList = (ListView) this.findViewById(R.id.tags_list_view);
		
		final ArrayList<String> tags = new ArrayList<String>();
		for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
			tags.add(TagList.getInstance().getTags().get(i).getName());
		}
		adapter = new ArrayAdapter<String>(this.getBaseContext(), R.layout.list_item, tags);

		tagList.setAdapter(adapter);
		
		tagList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				tagName.setText("");
				adapter.getItem(position);
				tagName.append(adapter.getItem(position));
				
				for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
					if (TagList.getInstance().getTags().get(i).getName().equals(adapter.getItem(position))) {
						tagURL.setText("");
						tagURL.append(TagList.getInstance().getTags().get(i).getURL());
					}
				}
				
			}
		});
		
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.accept:
			addTextTag();

			AddNameToTag.this.finish(); // This probably needs to go to the
										// ViewFullPic activity
			return true;
		case R.id.decline:
			// Intent j = new Intent(AddNameToTag.this, ViewFullPic.class);
			// startActivity(j);
			AddNameToTag.this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void addTextTag() {
		// Create tag and region and hopefully save it here
		Picture pic = PictureList.getInstance().getSelected();
		Region region = AddTag.getRegion();
		pic.addRegion(region);
		
		Tag tag = new Tag(tagName.getText().toString(), tagURL.getText()
				.toString());
		ArrayList<Region> regList = PictureList.getInstance().getSelected()
				.getRegions();
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

		TagList.getInstance().save();
		PictureList.getInstance().save();
	}

}
