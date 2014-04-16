package ca.ualberta.cs.taggingapp.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;

public class TagList extends SavedList<Tag>{
	private final static String FILENAME = "TagListSaved.json";
	private static TagList singleton = null;
	private ArrayList<Tag> tagList;
	private Tag selectedTag = null;
	private Context theContext;

	private TagList(Context theContext) {
		super(theContext);
		this.tagList = new ArrayList<Tag>();
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
		singleton.tagList = singleton.load();
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
		return tagList;
	}

	// These methods add or remove tags from the TagList
	public void addTag(Tag tag) {
		this.tagList.add(tag);
		save(this.tagList);
	}

	public void removeTag(Tag tag) {
		this.tagList.remove(tag);
		save(this.tagList);
	}

	public Tag getTag(int position) {
		return this.tagList.get(position);
	}

	public void setSelected(int position) {
		selectedTag = this.tagList.get(position);
	}

	public Tag getSelected() {
		return selectedTag;
	}

	// This method allows for searching the TagList
	public Tag findTag(String tagName) {
		Tag foundTag = null;

		Tag searchTag = new Tag(tagName);

		for (Tag tag : this.tagList) {
			if (tag.equals(searchTag)) {
				foundTag = tag;
				break;
			}
		}

		return foundTag;
	}
	
	public Tag getTagWithId(String theId) {
		for (Tag tag: this.tagList) {
			if (tag.getId().equals(theId)) {
				return tag;
			}
		}
		return null;
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}

	@Override
	public Tag[] getPrimativeArray(ArrayList<Tag> orignalArray) {
		Log.w("TagList", "getPrimativeArray");
		return orignalArray.toArray(new Tag[0]);
	}

	@Override
	public Type getType() {
		return new TypeToken<Tag[]>(){}.getType();
	}
}
