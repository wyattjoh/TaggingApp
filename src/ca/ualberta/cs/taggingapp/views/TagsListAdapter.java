package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TagsListAdapter extends BaseAdapter {

	private ArrayList<String> tags;
	private ArrayList<String> tagsClone;
	private LayoutInflater layoutInflater;
	/*
	public TagsListAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	*/
	@Override
	public int getCount() {
		return tags.size();
	}

	@Override
	public String getItem(int position) {
		return tags.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public TagsListAdapter(Context context, int resource, ArrayList<String> tags) {
		//super(context, resource);
		this.tags = tags;
		layoutInflater = LayoutInflater.from(context);
		tagsClone = new ArrayList<String>();
		tagsClone.addAll(tags);
	}
	
	static class ViewHolder {

		ImageView profilePic;
		TextView titleView;
		TextView authorView;
		TextView fragmentView;

	}
	
	public void filter(String query) {
		if (query != null) {
			tags.clear();
			for (String s : tagsClone) {
				if (s.matches("(?i)(.*)" + query + "(.*)")) {
					this.tags.add(s);
				}
			}
			this.notifyDataSetChanged();
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		/*if (convertView == null) {
			convertView = layoutInflater
					.inflate(R.layout.storylistlayout, null);
			holder = new ViewHolder();
			holder.titleView = (TextView) convertView
					.findViewById(R.id.storytitle);
			holder.authorView = (TextView) convertView
					.findViewById(R.id.storyauthor);
			holder.fragmentView = (TextView) convertView
					.findViewById(R.id.storyfragments);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.titleView.setText(stories.get(position).getTitle());
		holder.authorView.setText("By: " + stories.get(position).getAuthor());
		holder.fragmentView.setText("Fragments: "
				+ stories.get(position).getFrags().size());
*/
		return convertView;
	}

	

}
