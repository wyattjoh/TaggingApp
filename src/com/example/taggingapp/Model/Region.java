package com.example.taggingapp.Model;

public class Region {
	private int centerX = 0;
	private int centerY = 0;
	private int width = 0;
	private int height = 0;
	private int[] upperLeftCorner = {0, 0};
	private int[] lowerRightCorner = {0, 0};
	private Tag tag = null;
	private Picture picture = null;
	public int getCenterX() {
		return centerX;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
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
		this.upperLeftCorner[0] = x;
		this.upperLeftCorner[1] = y;
	}
	public int[] getUpperLeftCorner() {
		return upperLeftCorner;
	}
	public void setLowerRightCorner(int x, int y) {
		this.lowerRightCorner[0] = x;
		this.lowerRightCorner[1] = y;
	}
	public int[] getLowerRightCorner() {
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
	public void updateRegionFromCorners() {
		int centerX = (this.lowerRightCorner[0] + this.upperLeftCorner[0]) / 2;
		int centerY = (this.lowerRightCorner[1] + this.upperLeftCorner[1]) / 2;
		int height = this.lowerRightCorner[1] - this.upperLeftCorner[1];
		int width = this.lowerRightCorner[0] - this.upperLeftCorner[0];
		this.setCenterX(centerX);
		this.setCenterY(centerY);
		this.setHeight(height);
		this.setWidth(width);
	}
	public void updateRegionFromCenter() {
		int lowerRightCornerX = this.centerX + (this.width / 2);
		int lowerRightCornerY = this.centerY + (this.height / 2);
		int upperLeftCornerX = this.centerX - (this.width / 2);
		int upperLeftCornerY = this.centerY - (this.height / 2);
		this.setLowerRightCorner(lowerRightCornerX, lowerRightCornerY);
		this.setUpperLeftCorner(upperLeftCornerX, upperLeftCornerY);
	}
}
