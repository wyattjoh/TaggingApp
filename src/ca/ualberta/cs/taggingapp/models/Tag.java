package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class Tag {
	private String name;
	private String URL;
	private ArrayList<Region> taggedRegions = new ArrayList<Region>();
	
	
	public Tag(String name, String URL) {
		this.name = name;
		this.URL = URL;
	}
	
	public Tag(String name) {
		this.name = name;
		this.URL = null;
	}
	
	public Tag() {
		this.name = null;
		this.URL = null;
	}
	
	//Start of getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.toLowerCase();
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Tag)) {
			return false;
		}
		
		Tag object = (Tag) o;
		
		if (object.getName().equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}
}
