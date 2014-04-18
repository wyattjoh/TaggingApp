package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import ca.ualberta.cs.taggingapp.models.Tag;

public class TagsListAdapter extends BaseAdapter {

	private ArrayList<Tag> tags;

	public TagsListAdapter(Context context, int resource, ArrayList<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int getCount() {
		return tags.size();
	}

	@Override
	public Tag getItem(int position) {
		return tags.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return convertView;
	}
}
