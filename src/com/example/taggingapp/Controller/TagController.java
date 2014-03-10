package com.example.taggingapp.Controller;

import com.example.taggingapp.Model.Region;
import com.example.taggingapp.Model.Tag;

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
