package com.example.taggingapp.Controller;

import com.example.taggingapp.Model.Picture;
import com.example.taggingapp.Model.Region;
import com.example.taggingapp.Model.Tag;

public class RegionController {
	private int defaultSideLength = 64;
	public void createRegion(Picture picture, int x, int y) {
		int height = defaultSideLength;
		int width = defaultSideLength;
		Region region = new Region();
		region.setPicture(picture);
		region.setCenterX(x);
		region.setCenterY(y);
		region.setHeight(height);
		region.setWidth(width);
		picture.addRegion(region);
	}
	public void createRegion(Picture picture, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
		int x = (topLeftX + bottomRightX) / 2;
		int y = (topLeftY + bottomRightY) / 2;
		int height = (bottomRightY - topLeftY);
		int width = (bottomRightX - topLeftX);
		Region region = new Region();
		region.setPicture(picture);
		region.setCenterX(x);
		region.setCenterY(y);
		region.setHeight(height);
		region.setWidth(width);
		picture.addRegion(region);
	}
	public void resizeRegion(Region region) {
		//This method is very broken, plz fix
		region.setHeight(defaultSideLength);
		region.setWidth(defaultSideLength);
	}
	public void relocateRegion(Region region, int x, int y) {
		region.setCenterX(x);
		region.setCenterY(y);
	}
	public void removeRegion(Region region) {
		region.getPicture().removeRegion(region);
		if(region.getTag() != null) {
			region.getTag().removeTaggedRegion(region);
		}
	}
	public void editRegionTag(Region region, Tag tag) {
		if(region.getTag() != null) {
			region.getTag().removeTaggedRegion(region);
		}
		region.setTag(tag);
		tag.addTaggedRegion(region);
	}
}
