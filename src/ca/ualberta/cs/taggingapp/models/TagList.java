package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class TagList {
	private ArrayList<Tag> tags = new ArrayList<Tag>();
	public ArrayList<Tag> getTags() {
		return tags;
	}
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}
	public void removeTag(Tag tag) {
		this.tags.remove(tag);
	}
	public Tag findTag(String tag) {
		Tag foundTag = null;
		for(int i = 0; i < this.tags.size(); i++) {
			Tag searchTag = this.tags.get(i);
			if(searchTag.getName().toLowerCase() == tag.toLowerCase()) {
				foundTag = searchTag;
			}
		}
		return foundTag;
	}
}
