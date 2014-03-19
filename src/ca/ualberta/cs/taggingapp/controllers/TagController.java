package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;

public class TagController {
	public Tag createNewTag(Region region, String name, String URL) {
		Tag tag = new Tag();
		tag.addTaggedRegion(region);
		tag.setName(name);
		tag.setURL(URL);
		return tag;
	}
	public void editTagName(Tag tag, String name) {
		tag.setName(name);
	}
	public void editTagURL(Tag tag, String URL) {
		tag.setURL(URL);
	}
	public void addRegionToTag(Tag tag, Region region) {
		tag.addTaggedRegion(region);
	}
}
