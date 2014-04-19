package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Tag;

public class TagArrayAdapter extends ArrayAdapter<Tag> {
	private Boolean withIndex = false;

	public TagArrayAdapter(Context context, ArrayList<Tag> objects) {
		super(context, R.layout.list_item, objects);
	}
	
	public TagArrayAdapter(Context context, ArrayList<Tag> objects, Boolean withIndex) {
		this(context, objects);
		
		this.withIndex = withIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View theView = super.getView(position, convertView, parent);

		populateRowView(theView, position);

		return theView;
	}

	private void populateRowView(View theView, int position) {
		Tag theTag = getItem(position);

		TextView theRowView = (TextView) theView.findViewById(R.id.theRowText);
		
		if (withIndex) {
			theRowView.setText(Integer.toString(position + 1) + ". " + theTag.getName());
		} else {
			theRowView.setText(theTag.getName());
		}
	}

}
