package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;
import android.graphics.Bitmap;

public class Picture {
	private ArrayList<Region> regions = new ArrayList<Region>();
	private Bitmap picture = null;
	
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
	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
	//End of getters and setters
}
