package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

public class TagListController {
	private TagList tags = new TagList();
	private TagController controller = new TagController();
	public void addExistingTag(Tag tag) {
		tags.addTag(tag);
	}
	public void addNewTag(Region region, String name, String URL) {
		Tag tag = controller.createNewTag(region, name, URL);
		tags.addTag(tag);
	}
	public void removeTag(Tag tag) {
		tags.removeTag(tag);
	}
	public void checkTagUseAndRemove(Tag tag) {
		Tag testTag = tags.findTag(tag.getName());
		if(testTag.getTaggedRegions().isEmpty()) {
			tags.removeTag(testTag);
		}
	}
}
