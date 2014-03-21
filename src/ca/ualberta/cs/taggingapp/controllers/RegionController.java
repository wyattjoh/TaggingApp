package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;

public class RegionController {
	private int defaultSideLength = 64;
	public void createRegion(Picture picture, int x, int y) {
		Region region = new Region();
		region.setCenterX(x);
		region.setCenterY(y);
		region.setHeight(defaultSideLength);
		region.setWidth(defaultSideLength);
		region.setPicture(picture);
		region.updateRegionFromCenter();
		picture.addRegion(region);
	}
	public void createRegion(Picture picture, int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY) {
		Region region = new Region();
		region.setPicture(picture);
		region.setUpperLeftCorner(upperLeftX, upperLeftY);
		region.setLowerRightCorner(lowerRightX, lowerRightY);
		region.updateRegionFromCorners();
		picture.addRegion(region);
	}
	public void moveUpperLeftRegionCorner(Region region, int x, int y) {
		region.setUpperLeftCorner(x,y);
		region.updateRegionFromCorners();
	}
	public void moveLowerRightRegionCorner(Region region, int x, int y) {
		region.setLowerRightCorner(x, y);
		region.updateRegionFromCorners();
	}
	public void moveRegion(Region region, int x, int y) {
		region.setCenterX(x);
		region.setCenterY(y);
		region.updateRegionFromCenter();
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
