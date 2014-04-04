package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;


public class AppSerializer {
	private ArrayList<Picture> savedPictures = new ArrayList<Picture>();
	private ArrayList<Tag> savedTags = new ArrayList<Tag>();
	
	public void save() {
		savedPictures.addAll(PictureList.getInstance());
		savedTags.addAll(TagList.getInstance());
		for (Picture picture : savedPictures) {
			if (!picture.getRegions().isEmpty()) {
				for (Region region : picture.getRegions()) {
					region.
				}
			}
		}
	}
	
	public void load() {
		
	}
}
