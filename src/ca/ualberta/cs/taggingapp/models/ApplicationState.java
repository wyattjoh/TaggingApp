package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.content.Context;

public class ApplicationState {
	private static transient ApplicationState singleton = null;

	private transient SavedApplicationState theSavedApplicationState;
	private ArrayList<SavedPicture> savedPictures;
	private ArrayList<SavedTag> savedTags;

	public ApplicationState(Context applicationContext) {
		this();
		this.theSavedApplicationState = new SavedApplicationState(
				applicationContext);
	}

	public ApplicationState() {
		this.savedPictures = new ArrayList<SavedPicture>();
		this.savedTags = new ArrayList<SavedTag>();
	}

	public static ApplicationState createInstance(Context theContext) {
		if (singleton == null) {
			singleton = new ApplicationState(theContext);
		}
		return singleton;
	}

	public static ApplicationState getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Cant get ApplicationState before none created!");
		}
		return singleton;
	}

	public void save() {
		ArrayList<Picture> picturesToSave = PictureList.getInstance()
				.getPictureList();
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

		this.theSavedApplicationState.save(this);
	}

	public void load() {
		ApplicationState savedAppState = this.theSavedApplicationState.load();

		if (!savedAppState.getSavedTags().isEmpty()) {
			for (SavedTag tagToLoad : savedAppState.getSavedTags()) {
				Tag tag = tagToLoad.loadTag();
				TagList.getInstance().addTag(tag);
			}
		}
		if (!savedAppState.getSavedPictures().isEmpty()) {
			for (SavedPicture pictureToLoad : savedAppState.getSavedPictures()) {
				Picture picture = pictureToLoad.loadPicture();
				PictureList.getInstance().addPicture(picture);
			}
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
