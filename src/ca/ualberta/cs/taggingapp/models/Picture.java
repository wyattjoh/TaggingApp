package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class Picture {
	private ArrayList<Region> regions = new ArrayList<Region>();
	private String picture = null;
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
	public String getPicture() {
		return picture;
	}
	public void setBitmap(String picture) {
		this.picture = picture;
	}
}
