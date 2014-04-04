package ca.ualberta.cs.taggingapp.models;


public class SavedRegion {
	private int upperLeftX;
	private int upperLeftY;
	private int lowerRightX;
	private int lowerRightY;
	private String tagID = null;
	
	public SavedRegion(Region region) {
		this.upperLeftX = region.getUpperLeftCorner().x;
		this.upperLeftY = region.getUpperLeftCorner().y;
		this.lowerRightX = region.getLowerRightCorner().x;
		this.lowerRightY = region.getLowerRightCorner().y;
		this.tagID = region.getTag().getName();
	}

	
	public int getUpperLeftX() {
		return upperLeftX;
	}

	
	public void setUpperLeftX(int upperLeftX) {
		this.upperLeftX = upperLeftX;
	}

	
	public int getUpperLeftY() {
		return upperLeftY;
	}

	
	public void setUpperLeftY(int upperLeftY) {
		this.upperLeftY = upperLeftY;
	}

	
	public int getLowerRightX() {
		return lowerRightX;
	}

	
	public void setLowerRightX(int lowerRightX) {
		this.lowerRightX = lowerRightX;
	}

	
	public int getLowerRightY() {
		return lowerRightY;
	}

	
	public void setLowerRightY(int lowerRightY) {
		this.lowerRightY = lowerRightY;
	}

	
	public String getTagID() {
		return tagID;
	}

	
	public void setTagID(String tagID) {
		this.tagID = tagID;
	}
	
}
