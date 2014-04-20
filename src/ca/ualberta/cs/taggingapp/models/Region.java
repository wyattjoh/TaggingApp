package ca.ualberta.cs.taggingapp.models;

import android.graphics.Point;

/**
 * Determines the region that has been selecting when tagging is performed in
 * our application.
 * 
 * */

public class Region extends ManagedObject {
	private Point center = new Point(0, 0);
	private int width = 0;
	private int height = 0;
	private Point upperLeftCorner = new Point(0, 0);
	private Point lowerRightCorner = new Point(0, 0);
	private String tagId = null;
	private String pictureId = null;

	private Region() {
		super();
	}

	public Region(Picture picture, Point center) {
		this();

		this.height = 64;
		this.width = 64;
		this.pictureId = picture.getId();
		this.setCenter(center);
	}

	public Region(Picture picture, int x, int y) {
		this();

		this.height = 64;
		this.width = 64;
		this.pictureId = picture.getId();
		this.setCenter(x, y);
	}

	public Region(Picture picture, Point upperLeft, Point lowerRight) {
		this();

		this.pictureId = picture.getId();
		this.upperLeftCorner = upperLeft;
		this.lowerRightCorner = lowerRight;
		this.updateRegionFromCorners();
	}

	public Region(Picture picture, int upperLeftX, int upperLeftY,
			int lowerRightX, int lowerRightY) {
		this();

		this.pictureId = picture.getId();
		this.upperLeftCorner.x = upperLeftX;
		this.upperLeftCorner.y = upperLeftY;
		this.lowerRightCorner.x = lowerRightX;
		this.lowerRightCorner.y = lowerRightY;
		this.updateRegionFromCorners();
	}

	// Start of getters and setters
	/**
	 * @param x
	 * @param y
	 */
	public void setCenter(int x, int y) {
		this.center.x = x;
		this.center.y = y;
		this.updateRegionFromCenter();
	}

	/**
	 * @param point
	 */
	public void setCenter(Point point) {
		this.center = point;
		this.updateRegionFromCenter();
	}

	/**
	 * @return
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
		this.updateRegionFromCenter();
	}

	/**
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
		this.updateRegionFromCenter();
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setUpperLeftCorner(int x, int y) {
		this.upperLeftCorner.x = x;
		this.upperLeftCorner.y = y;
		this.updateRegionFromCorners();
	}

	/**
	 * @param point
	 */
	public void setUpperLeftCorner(Point point) {
		this.upperLeftCorner = point;
		this.updateRegionFromCorners();
	}

	/**
	 * @return
	 */
	public Point getUpperLeftCorner() {
		return upperLeftCorner;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setLowerRightCorner(int x, int y) {
		this.lowerRightCorner.x = x;
		this.lowerRightCorner.y = y;
		this.updateRegionFromCorners();
	}

	/**
	 * @param point
	 */
	public void setLowerRightCorner(Point point) {
		this.lowerRightCorner = point;
		this.updateRegionFromCorners();
	}

	/**
	 * @return
	 */
	public Point getLowerRightCorner() {
		return lowerRightCorner;
	}

	/**
	 * @return
	 */
	public Tag getTag() {
		return TagList.getInstance().get(tagId);
	}

	/**
	 * @param tag
	 */
	public void setTag(Tag tag) {
		this.tagId = tag.getId();
	}

	/**
	 * @return
	 */
	public Picture getPicture() {
		return PictureList.getInstance().get(pictureId);
	}

	/**
	 * @param picture
	 */
	public void setPicture(Picture picture) {
		this.pictureId = picture.getId();
	}

	/**
	 * @param thePicture
	 * @return
	 */
	public Boolean isForPicture(Picture thePicture) {
		return thePicture.getId().equals(pictureId);
	}

	/**
	 * @param theTag
	 * @return
	 */
	public Boolean isForTag(Tag theTag) {
		return theTag.getId().equals(tagId);
	}

	// End of getters and setters

	/**
	 * 
	 */
	public void removeRegion() {
		this.getPicture().removeRegion(this);
		if (this.getTag() != null) {
			this.getTag().addRegion(this);
		}
	}

	/**
	 * @param tag
	 */
	public void editRegionTag(Tag tag) {
		if (this.getTag() != null) {
			this.getTag().removeRegion(this);
		}
		this.setTag(tag);

		Tag theTag = TagList.getInstance().get(this.tagId);

		theTag.addRegion(this);
	}

	/*
	 * These methods update the attributes of the region based on input from the
	 * center of the region or the corners. One of two of these methods is used
	 * on create or edit, depending on the tagging style or type of edit.
	 */

	/**
	 * Updates the center point, width and height of the region given corner
	 * points
	 */
	public void updateRegionFromCorners() {
		int centerX = (this.lowerRightCorner.x + this.upperLeftCorner.x) / 2;
		int centerY = (this.lowerRightCorner.y + this.upperLeftCorner.y) / 2;
		int height = this.lowerRightCorner.y - this.upperLeftCorner.y;
		int width = this.lowerRightCorner.x - this.upperLeftCorner.x;
		this.center = new Point(centerX, centerY);
		this.setHeight(height);
		this.setWidth(width);
	}

	/**
	 * Updates the corner points given a new center, using the current width and
	 * height
	 */
	public void updateRegionFromCenter() {
		int lowerRightCornerX = this.center.x + (this.width / 2);
		int lowerRightCornerY = this.center.y + (this.height / 2);
		int upperLeftCornerX = this.center.x - (this.width / 2);
		int upperLeftCornerY = this.center.y - (this.height / 2);
		Point upperLeft = new Point(upperLeftCornerX, upperLeftCornerY);
		Point lowerRight = new Point(lowerRightCornerX, lowerRightCornerY);
		this.upperLeftCorner = upperLeft;
		this.lowerRightCorner = lowerRight;
	}
}
