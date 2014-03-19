package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class Tag {
	private String name = null;
	private String URL = null;
	private ArrayList<Region> taggedRegions = new ArrayList<Region>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public ArrayList<Region> getTaggedRegions() {
		return taggedRegions;
	}
	public void addTaggedRegion(Region region) {
		this.taggedRegions.add(region);
	}
	public void removeTaggedRegion(Region region) {
		this.taggedRegions.remove(region);
	}
}
