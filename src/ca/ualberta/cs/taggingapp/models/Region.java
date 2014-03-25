package ca.ualberta.cs.taggingapp.models;

import android.graphics.Point;

/*
 * This class represents a tagged area on a photo, it's constructed using the
 * RegionController rather than a constructor since it has a complicated setup
 */
public class Region {
	private Point center = new Point(0,0);
	private int width = 0;
	private int height = 0;
	private Point upperLeftCorner = new Point(0,0);
	private Point lowerRightCorner = new Point(0,0);
	private Tag tag = null;
	private Picture picture = null;
	
	public Region(Picture picture, int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY) {
		this.picture = picture;
		this.upperLeftCorner.x = upperLeftX;
		this.upperLeftCorner.y = upperLeftY;
		this.lowerRightCorner.x = lowerRightX;
		this.lowerRightCorner.y = lowerRightY;
		this.updateRegionFromCorners();
	}
	
	public Region(Picture picture, int x, int y) {
		this.height = 64;
		this.width = 64;
		this.picture = picture;
		this.setCenter(x, y);
	}
	
	//Start of getters and setters
	public void setCenter(int x, int y) {
		this.center.x = x;
		this.center.y = y;
		this.updateRegionFromCenter();
	}
	public Point getCenter() {
		return center;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setUpperLeftCorner(int x, int y) {
		this.upperLeftCorner.x = x;
		this.upperLeftCorner.y = y;
		this.updateRegionFromCorners();
	}
	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}
	public void setLowerRightCorner(int x, int y) {
		this.lowerRightCorner.x = x;
		this.lowerRightCorner.y = y;
		this.updateRegionFromCorners();
	}
	public Point getLowerRightCorner() {
		return lowerRightCorner;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	//End of getters and setters
	
	public void removeRegion() {
		this.getPicture().removeRegion(this);
		if(this.getTag() != null) {
			this.getTag().removeTaggedRegion(this);
		}
	}
	
	public void editRegionTag(Tag tag) {
		if(this.getTag() != null) {
			this.getTag().removeTaggedRegion(this);
		}
		this.setTag(tag);
		this.tag.addTaggedRegion(this);
	}
	
	/*
	 * These methods update the attributes of the region based on input from the
	 * center of the region or the corners. One of two of these methods is used
	 * on create or edit, depending on the tagging style or type of edit.
	 */
	//Updates the center point, width and height of the region given corner points
	public void updateRegionFromCorners() {
		int centerX = (this.lowerRightCorner.x + this.upperLeftCorner.x) / 2;
		int centerY = (this.lowerRightCorner.y + this.upperLeftCorner.y) / 2;
		int height = this.lowerRightCorner.y - this.upperLeftCorner.y;
		int width = this.lowerRightCorner.x - this.upperLeftCorner.x;
		this.setCenter(centerX, centerY);
		this.setHeight(height);
		this.setWidth(width);
	}
	//Updates the corner points given a new center, using the current width and height
	public void updateRegionFromCenter() {
		int lowerRightCornerX = this.center.x + (this.width / 2);
		int lowerRightCornerY = this.center.y + (this.height / 2);
		int upperLeftCornerX = this.center.x - (this.width / 2);
		int upperLeftCornerY = this.center.y - (this.height / 2);
		this.setLowerRightCorner(lowerRightCornerX, lowerRightCornerY);
		this.setUpperLeftCorner(upperLeftCornerX, upperLeftCornerY);
	}
}
