package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

/**
 * @author Tagging Group This activity represents the 'Tags' tab in the 'home
 *         screen'. When the right tab is selected, this activity's XML is
 *         displayed below the tab selector.
 * 
 */
public class TagsListFragment extends Fragment {

	private TagArrayAdapter adapter;
	private EditText searchView;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Set the adapters and the text input field for the search view
		final View rootView = inflater.inflate(R.layout.fragment_tags_list,
				container, false);
		listView = (ListView) rootView.findViewById(R.id.tags_list_view);
		searchView = (EditText) rootView.findViewById(R.id.tag_search);

		populateView();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(rootView.getContext(),
						TagRefinedImages.class);

				Tag theTag = adapter.getItem(position);

				i.putExtra(TagRefinedImages.TAG_ID, theTag.getId());
				startActivity(i);
			}
		});

		searchView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				TagsListFragment.this.adapter.getFilter().filter(cs);
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

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		populateView();
	}

	protected void populateView() {
		ArrayList<Tag> tags = TagList.getInstance().getArrayList();

		adapter = new TagArrayAdapter(getActivity().getApplicationContext(),
				tags);

		listView.setAdapter(adapter);
	}

}