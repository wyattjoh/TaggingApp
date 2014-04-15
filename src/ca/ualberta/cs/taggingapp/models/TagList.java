package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.content.Context;

public class TagList {
	private final static String FILENAME = "TagList.json";
	private static TagList singleton = null;
	private ArrayList<Tag> tagList;
	private Tag selectedTag = null;
	private Context theContext;

	private TagList() {
		this.tagList = new ArrayList<Tag>();
	}

	public void setTheContext(Context theContext) {
		this.theContext = theContext;
	}

	public Context getTheContext() {
		return this.theContext;
	}

	public static TagList createInstance(Context theContext) {
		singleton = new TagList();
		singleton.setTheContext(theContext);
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
		// save();
	}

	public void removeTag(Tag tag) {
		this.tagList.remove(tag);

		// save();
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
}
