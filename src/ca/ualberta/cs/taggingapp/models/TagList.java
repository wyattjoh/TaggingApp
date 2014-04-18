package ca.ualberta.cs.taggingapp.models;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

/**
 * Creates and maintains a list of tags.
 * 
 * */

public class TagList extends SavedList<Tag> {
	private final static String FILENAME = "TagListSaved.json";
	private static TagList singleton = null;
	private Tag selectedTag = null;
	private Context theContext;

	private TagList(Context theContext) {
		super(theContext);
	}

	public void setTheContext(Context theContext) {
		this.theContext = theContext;
	}

	public Context getTheContext() {
		return this.theContext;
	}

	public static TagList createInstance(Context theContext) {
		singleton = new TagList(theContext);
		singleton.setTheContext(theContext);
		singleton.theList = singleton.load();
		return singleton;
	}

	public static TagList getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Cannot get shared of TagList when none created!");
		}
		return singleton;
	}

	public ArrayList<Tag> getTags() {
		return theList;
	}

	// These methods add or remove tags from the TagList
	public void addTag(Tag tag) {
		this.theList.add(tag);
		save();
	}

	public void removeTag(Tag tag) {
		this.theList.remove(tag);
		save();
	}

	public Tag getTag(int position) {
		return this.theList.get(position);
	}

	public void setSelected(int position) {
		selectedTag = this.theList.get(position);
	}

	public Tag getSelected() {
		return selectedTag;
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}

	@Override
	public Tag[] getPrimativeArray(ArrayList<Tag> orignalArray) {
		return orignalArray.toArray(new Tag[0]);
	}

	@Override
	public Type getType() {
		return new TypeToken<Tag[]>() {
		}.getType();
	}
}
