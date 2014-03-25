package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import ca.ualberta.cs.taggingapp.views.PictureAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Picture {
	private ArrayList<Region> regions;
	private transient Bitmap picture = null;
	private String pictureUri = null;
	
	public Picture() {
		regions = new ArrayList<Region>();
	}
	
	//Start of getters and setters
	public ArrayList<Region> getRegions() {
		return regions;
	}
	public void addRegion(Region region) {
		this.regions.add(region);
	}
	public void removeRegion(Region region) {
		this.regions.remove(region);
	}
	public void removeAllRegions() {
		this.regions.clear();
	}
	public Bitmap getPicture() {
		if (picture == null && pictureUri != null) {
			// TODO: Load the picture from the URI
			
		}
		
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}
	//End of getters and setters
}
