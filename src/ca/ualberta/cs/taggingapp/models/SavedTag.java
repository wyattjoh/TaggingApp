package ca.ualberta.cs.taggingapp.models;


public class SavedTag {
	private String name = null;
	private String URL = null;
	
	public SavedTag(Tag tagToSave) {
		this.name = tagToSave.getName();
		this.URL = tagToSave.getURL();
	}

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
	
	public Tag loadTag() {
		Tag tag = new Tag();
		return tag;
	}
}
