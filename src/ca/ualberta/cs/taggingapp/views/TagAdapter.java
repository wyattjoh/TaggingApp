package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Tag;

/**
 * @author Tagging Group A simple adapter class that maps the tags list to a
 *         listview.
 */
public class TagAdapter extends ArrayAdapter<Tag> {
	private ArrayList<Tag> theTags;
	private int theLayout;
	private LayoutInflater layoutInflater = null;

	// Constructor which sets the proper values. The third variable 'theTags'
	// is the most important, as it is the arraylist containing the proper
	// values that will be mapped to the listview
	public TagAdapter(Activity theActivity, int theLayout,
			ArrayList<Tag> theTags) {
		super(theActivity, theLayout, theTags);
		this.layoutInflater = theActivity.getLayoutInflater();
		this.theLayout = theLayout;
		this.theTags = theTags;
	}

	@Override
	public int getCount() {
		return this.theTags.size();
	}

	@Override
	public Tag getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.theTags.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int thePosition, View convertView, ViewGroup parent) {
		View postRowView = convertView;

		if (postRowView == null) {
			postRowView = layoutInflater.inflate(this.theLayout, parent, false);
		}

		// set row elements to the required data
		populateRowView(postRowView, thePosition);

		return postRowView;
	}

	private void populateRowView(View postRowView, int thePosition) {
		TextView theTextView = (TextView) postRowView
				.findViewById(R.id.theRowText);
		// Return the proper tag at 'thePosition'
		Tag theTag = getItem(thePosition);

		theTextView.setText(theTag.getName());
	}

}
