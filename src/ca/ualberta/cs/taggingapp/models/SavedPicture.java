package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.net.Uri;

public class SavedPicture {
	private ArrayList<SavedRegion> savedRegions;
	private Uri savedPictureUri = null;

	public SavedPicture(Picture pictureToSave) {

		this.savedRegions = new ArrayList<SavedRegion>();

		if (!pictureToSave.getRegions().isEmpty()) {
			for (Region regionToSave : pictureToSave.getRegions()) {
				SavedRegion savedRegion = new SavedRegion(regionToSave);
				this.savedRegions.add(savedRegion);

			}
		}
		this.savedPictureUri = pictureToSave.getPictureUri();
	}

	public ArrayList<SavedRegion> getSavedRegions() {
		return savedRegions;
	}

	public void setSavedRegions(ArrayList<SavedRegion> savedRegions) {
		this.savedRegions = savedRegions;
	}

	public Uri getSavedPictureUri() {
		return savedPictureUri;
	}

	public void setSavedPictureUri(Uri savedPictureUri) {
		this.savedPictureUri = savedPictureUri;
	}

	public Picture loadPicture() {
		Picture picture = new Picture(this.savedPictureUri);
		if (!this.savedRegions.isEmpty()) {
			for (SavedRegion savedRegion : this.savedRegions) {
				Region loadedRegion = savedRegion.loadRegion(picture);
				picture.addRegion(loadedRegion);
			}
		}
		return picture;
	}
}
