package ca.ualberta.cs.taggingapp.models;

import android.graphics.Point;

public class SavedRegion {
	private Point upperLeft;
	private Point lowerRight;
	private String tagID = null;

	public SavedRegion(Region region) {
		this.upperLeft = new Point(region.getUpperLeftCorner());
		this.lowerRight = new Point(region.getLowerRightCorner());
		this.tagID = region.getTag().getName();
	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public Point getLowerRight() {
		return lowerRight;
	}

	public void setLowerRight(Point lowerRight) {
		this.lowerRight = lowerRight;
	}

	public String getTagID() {
		return tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public Region loadRegion(Picture picture) {
		Region region = new Region(picture, this.upperLeft, this.lowerRight);
		Tag tag = TagList.getInstance().findTag(this.tagID);
		region.editRegionTag(tag);
		return region;
	}
}
