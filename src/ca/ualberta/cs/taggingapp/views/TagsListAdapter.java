package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.models.Tag;

public class TagsListAdapter extends BaseAdapter {

	private ArrayList<Tag> tags;
	private LayoutInflater layoutInflater;

	public TagsListAdapter(Context context, int resource, ArrayList<Tag> tags) {
		//super(context, resource);
		this.tags = tags;
		layoutInflater = LayoutInflater.from(context);
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
	
//	public void filter(String query) {
//		if (query != null) {
//			tags.clear();
//			for (Tag tag : tags) {
//				String tagName = tag.getName();
//				if (tagName.matches("(?i)(.*)" + query + "(.*)")) {
//					this.tags.add(tagName);
//				}
//			}
//			this.notifyDataSetChanged();
//		}
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return convertView;
	}
}
