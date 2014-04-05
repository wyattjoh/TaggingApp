package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Picture {
	private ArrayList<Region> regions;
	private transient Bitmap picture = null;
	private String pictureUri = null;
	
	public Picture() {
		this.regions = new ArrayList<Region>();
	}
	
	public Picture(Bitmap bitmap) {
		this.regions = new ArrayList<Region>();
		this.picture = bitmap;
	}
	
	public Picture(String uri) {
		this.regions = new ArrayList<Region>();
		this.pictureUri = uri;
	}
	
	public Picture(Bitmap bitmap, String uri) {
		this.regions = new ArrayList<Region>();
		this.picture = bitmap;
		this.pictureUri = uri;
	}
	
	//Start of getters and setters
	public ArrayList<Region> getRegions() {
		return regions;
	}
	
	public void addRegion(Region region) {
		this.regions.add(region);
	}
	
	public void removeRegion(Region region) {
		this.regions.remove(region);
	}
	
	public void removeAllRegions() {
		this.regions.clear();
	}
	
	public String getPictureUri() {
		return this.pictureUri;
	}
	
	public void setPictureUri(String uri) {
		this.pictureUri = uri;
	}
	
	public Bitmap getPicture() {
		if (picture == null && pictureUri != null) {
			// TODO: Load the picture from the URI
			
		}
		
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
	//End of getters and setters
}
