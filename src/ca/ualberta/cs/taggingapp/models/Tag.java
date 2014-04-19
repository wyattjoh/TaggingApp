package ca.ualberta.cs.taggingapp.models;

/**
 * A tag class that models all properties of a tag.
 * 
 * */

public class Tag extends RegionObject {
	private String name;
	private String URL;

	public Tag() {
		super();

		this.name = null;
		this.URL = null;
	}

	public Tag(String name) {
		this();

		this.name = name;
	}

	public Tag(String name, String URL) {
		this();

		this.name = name;
		this.URL = URL;
	}

	// Start of getters and setters
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

	/*
	 * (non-Javadoc)
	 * 
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
		} else {
			return false;
		}
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}
}
