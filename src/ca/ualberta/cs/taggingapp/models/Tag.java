package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class Tag {
	private String name = null;
	private String URL = null;
	private ArrayList<Region> taggedRegions = new ArrayList<Region>();
	
	//Start of getters and setters
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
	//End of getters and setters
	
	//These methods associates or dissociates a region with a tag
	public void addTaggedRegion(Region region) {
		this.taggedRegions.add(region);
	}
	public void removeTaggedRegion(Region region) {
		this.taggedRegions.remove(region);
	}
}
