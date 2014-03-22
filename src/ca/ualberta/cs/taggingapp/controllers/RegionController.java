package ca.ualberta.cs.taggingapp.controllers;

import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;

/*
 * This class sets up and modifies Regions given either a center point or
 * opposing corners of the desired region
 */
public class RegionController {
	private int defaultSideLength = 64;
	public void createRegion(Picture picture, int x, int y) {
		Region region = new Region();
		region.setCenter(x, y);
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
		region.setCenter(x,y);
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
