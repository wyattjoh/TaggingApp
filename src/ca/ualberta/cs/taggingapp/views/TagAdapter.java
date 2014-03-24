package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import ca.ualberta.cs.taggingapp.models.Tag;

public class TagAdapter extends BaseAdapter {
	private ArrayList<Tag> theTags;
	private int theLayout;
	private LayoutInflater layoutInflater = null;

	public TagAdapter(Activity theActivity, int theLayout, ArrayList<Tag> theTags) {
		this.layoutInflater = theActivity.getLayoutInflater();
		this.theLayout = theLayout;
		this.theTags = theTags;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.theTags.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.theTags.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
