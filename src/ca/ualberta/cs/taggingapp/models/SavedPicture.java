package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.graphics.Bitmap;


public class SavedPicture {
	private ArrayList<SavedRegion> savedRegions;
	private transient Bitmap savedPicture = null;
	private String savedPictureUri = null;
	
	public SavedPicture(Picture pictureToSave) {
		if (!pictureToSave.getRegions().isEmpty()) {
			for (Region regionToSave : pictureToSave.getRegions()) {
				SavedRegion savedRegion = new SavedRegion(regionToSave);
				this.savedRegions.add(savedRegion);
			}
		}
		this.savedPicture = pictureToSave.getPicture();
		this.savedPictureUri = pictureToSave.getPictureUri();
	}

	public ArrayList<SavedRegion> getSavedRegions() {
		return savedRegions;
	}

	public void setSavedRegions(ArrayList<SavedRegion> savedRegions) {
		this.savedRegions = savedRegions;
	}

	public Bitmap getSavedPicture() {
		return savedPicture;
	}
	
	public void setSavedPicture(Bitmap savedPicture) {
		this.savedPicture = savedPicture;
	}
	
	public String getSavedPictureUri() {
		return savedPictureUri;
	}

	public void setSavedPictureUri(String savedPictureUri) {
		this.savedPictureUri = savedPictureUri;
	}
	
	public Picture loadPicture() {
		Picture picture = new Picture(this.savedPicture, this.savedPictureUri);
		if (!this.savedRegions.isEmpty()) {
			for (SavedRegion savedRegion : this.savedRegions) {
				Region loadedRegion = savedRegion.loadRegion(picture);
				picture.addRegion(loadedRegion);
			}
		}
		return picture;
	}
}
