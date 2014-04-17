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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

public class TagsListFragment extends Fragment {

	private ArrayAdapter <String> adapter;
	private EditText searchView;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_tags_list,
				container, false);
		listView = (ListView) rootView.findViewById(R.id.tags_list_view);
		searchView = (EditText) rootView.findViewById(R.id.tag_search);
		
		ArrayList<String> tags = new ArrayList<String>();
		for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
			tags.add(TagList.getInstance().getTags().get(i).getName());
		}
		adapter = new ArrayAdapter(getActivity(), R.layout.list_item, tags);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(rootView.getContext(),
						TagRefinedImages.class);
				//i.putExtra("tagName", TagList.getInstance().getTags().get(position).getName());
				i.putExtra("tagName", adapter.getItem(position));
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
		// TODO Auto-generated method stub
		super.onResume();
		ArrayList<String> tags = new ArrayList<String>();
		for (int i = 0; i < TagList.getInstance().getTags().size(); i++) {
			tags.add(TagList.getInstance().getTags().get(i).getName());
		}
		adapter = new ArrayAdapter(getActivity(), R.layout.list_item, tags);

		listView.setAdapter(adapter);
	}

}