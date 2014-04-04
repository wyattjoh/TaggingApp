package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;


public class AppSerializer {
	private ArrayList<SavedPicture> savedPictures;
	private ArrayList<SavedTag> savedTags;
	
	public AppSerializer() {
		this.savedPictures = new ArrayList<SavedPicture>();
		this.savedTags = new ArrayList<SavedTag>();
	}
	
	public void save() {
		ArrayList<Picture> picturesToSave = PictureList.getInstance().getPictureList();
		ArrayList<Tag> tagsToSave = TagList.getInstance().getTags();
		if (!picturesToSave.isEmpty()) {
			for (Picture picture : picturesToSave) {
				SavedPicture savedPicture = new SavedPicture(picture);
				savedPictures.add(savedPicture);
			}	
		}
		if (!tagsToSave.isEmpty()) {
			for (Tag tag : tagsToSave) {
				SavedTag savedTag = new SavedTag(tag);
				savedTags.add(savedTag);
			}
		}
	}
	
	public void load() {
		if (!savedTags.isEmpty()) {
			for (SavedTag tagToLoad : savedTags) {
				Tag tag = tagToLoad.loadTag();
				TagList.getInstance().addTag(tag);
			}
		}
		if (!savedPictures.isEmpty()) {
			for (SavedPicture pictureToLoad : savedPictures) {
				Picture picture = pictureToLoad.loadPicture();
				PictureList.getInstance().addPicture(picture);			}
		}
	}
	
	public ArrayList<SavedPicture> getSavedPictures() {	
		return savedPictures;
	}

	public void setSavedPictures(ArrayList<SavedPicture> savedPictures) {
		this.savedPictures = savedPictures;
	}
	
	public ArrayList<SavedTag> getSavedTags() {	
		return savedTags;
	}

	public void setSavedTags(ArrayList<SavedTag> savedTags) {	
		this.savedTags = savedTags;
	}
}
