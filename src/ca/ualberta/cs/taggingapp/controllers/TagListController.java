package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

public class TagListController {
	
	//This class allows easy editing and setup of TagLists and TagControllers
	private TagList tags = TagList.getInstance();
	private TagController controller = new TagController();
	
	//Tag list edit methods
	public void addExistingTag(Tag tag) {
		tags.addTag(tag);
	}
	public Tag addNewTag(Region region, String name, String URL) {
		Tag tag = controller.createNewTag(region, name, URL);
		tags.addTag(tag);
		return tag;
	}
	public void removeTag(Tag tag) {
		tags.removeTag(tag);
	}
	
	/*
	 * This method will allow you to see if a tag is still in use by any region
	 * The tag you pass is checked and if no regions reference it, it is removed
	 */
	public void checkTagUseAndRemove(Tag tag) {
		Tag testTag = tags.findTag(tag.getName());
		if(testTag.getTaggedRegions().isEmpty()) {
			tags.removeTag(testTag);
		}
	}
}
