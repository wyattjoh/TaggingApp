package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Tag;

/**
 * Manages the list of tags
 * 
 * @author wyatt
 * 
 */
public class TagArrayAdapter extends ArrayAdapter<Tag> {
	private Boolean withIndex = false;
	private NameFilter theFilter = null;
	private ArrayList<Tag> theBackupObjects = new ArrayList<Tag>();

	/**
	 * @param context
	 * @param objects
	 */
	public TagArrayAdapter(Context context, ArrayList<Tag> objects) {
		super(context, R.layout.list_item, objects);
		this.theFilter = new NameFilter();

		// Complete a shallow copy
		for (Tag aTag : objects) {
			this.theBackupObjects.add(aTag);
		}
	}

	/**
	 * @param context
	 * @param objects
	 * @param withIndex
	 */
	public TagArrayAdapter(Context context, ArrayList<Tag> objects,
			Boolean withIndex) {
		this(context, objects);

		this.withIndex = withIndex;
	}

	/**
	 * Reloads the list view
	 */
	private void reloadDataFromBackup() {
		this.clear();
		this.addAll(theBackupObjects);
		notifyDataSetChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
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

	/**
	 * @param theView
	 * @param position
	 */
	private void populateRowView(View theView, int position) {
		Tag theTag = getItem(position);

		TextView theRowView = (TextView) theView.findViewById(R.id.theRowText);

		if (withIndex) {
			theRowView.setText(Integer.toString(position + 1) + ". "
					+ theTag.getName());
		} else {
			theRowView.setText(theTag.getName());
		}
	}

	/**
	 * @author wyatt
	 * 
	 */
	private class NameFilter extends Filter {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Filter#performFiltering(java.lang.CharSequence)
		 */
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResults = new FilterResults();
			ArrayList<Tag> theResults = new ArrayList<Tag>();

			int theLength = TagArrayAdapter.this.getCount();

			String theConstraint = constraint.toString();

			if (theConstraint.length() > 0) {

				for (int i = 0; i < theLength; i++) {
					Tag theTag = TagArrayAdapter.this.getItem(i);

					if (theTag.getName().startsWith(theConstraint)) {
						theResults.add(theTag);
					}
				}

			}

			filterResults.values = theResults;
			filterResults.count = theResults.size();

			return filterResults;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Filter#publishResults(java.lang.CharSequence,
		 * android.widget.Filter.FilterResults)
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			ArrayList<Tag> theResults = (ArrayList<Tag>) results.values;

			if (theResults.size() > 0) {
				TagArrayAdapter.this.clear();
				TagArrayAdapter.this.addAll(theResults);
				notifyDataSetChanged();
			} else {
				reloadDataFromBackup();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getFilter()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getFilter()
	 */
	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return this.theFilter;
	};
}
