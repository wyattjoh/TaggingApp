package ca.ualberta.cs.taggingapp.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import com.google.gson.reflect.TypeToken;

public class RegionList extends SavedList<Region> {
	private final static String FILENAME = "RegionListListSaved.json";
	private static RegionList singleton = null;
	private Context theContext;

	private RegionList(Context theContext) {
		super(theContext);
	}

	public void setTheContext(Context theContext) {
		this.theContext = theContext;
	}

	public Context getTheContext() {
		return this.theContext;
	}

	public static RegionList createInstance(Context theContext) {
		singleton = new RegionList(theContext);
		singleton.setTheContext(theContext);
		singleton.theList = singleton.load();
		return singleton;
	}

	public static RegionList getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Cannot get shared of TagList when none created!");
		}
		return singleton;
	}

	protected ArrayList<Region> getAllRegionsForTag(Tag theTag) {
		ArrayList<Region> theRegions = new ArrayList<Region>();

		for (Region region : getArrayList()) {
			if (region.isForTag(theTag)) {
				theRegions.add(region);
			}
		}

		return theRegions;
	}

	protected ArrayList<Region> getAllRegionsForPicture(Picture thePicture) {
		ArrayList<Region> theRegions = new ArrayList<Region>();

		for (Region region : getArrayList()) {
			if (region.isForPicture(thePicture)) {
				theRegions.add(region);
			}
		}

		return theRegions;
	}

	public ArrayList<Picture> getAllPicturesFromTag(Tag theTag) {
		ArrayList<Picture> thePictureList = new ArrayList<Picture>();

		for (Region region : getAllRegionsForTag(theTag)) {
			thePictureList.add(region.getPicture());
		}

		return thePictureList;
	}

	public ArrayList<Tag> getAllTagsFromPicture(Picture thePic) {
		ArrayList<Tag> theTagList = new ArrayList<Tag>();

		for (Region region : getAllRegionsForPicture(thePic)) {
			theTagList.add(region.getTag());
		}

		return theTagList;
	}

	public void deleteAllRegionsForTag(Tag theTagWeAreDeleting) {
		ArrayList<Region> theRegionsThatAreForTag = getAllRegionsForTag(theTagWeAreDeleting);

		for (Region theRegion : theRegionsThatAreForTag) {
			Picture thePicture = theRegion.getPicture();
			thePicture.removeRegion(theRegion);

			remove(theRegion);
		}

		PictureList.getInstance().save();
		save();
	}

	public void deleteAllRegionsForPicture(Picture thePictureWeAreDeleting) {
		ArrayList<Region> theRegionsThatAreForPicture = getAllRegionsForPicture(thePictureWeAreDeleting);

		for (Region theRegion : theRegionsThatAreForPicture) {
			Tag theTag = theRegion.getTag();
			theTag.removeRegion(theRegion);

			remove(theRegion);
		}

		TagList.getInstance().save();
		save();
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}

	@Override
	public Region[] getPrimativeArray(ArrayList<Region> orignalArray) {
		return orignalArray.toArray(new Region[0]);
	}

	@Override
	public Type getType() {
		return new TypeToken<Region[]>() {
		}.getType();
	}

}
