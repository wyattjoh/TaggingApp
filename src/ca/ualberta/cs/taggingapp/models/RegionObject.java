package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class RegionObject extends ManagedObject {

	protected ArrayList<String> regions;

	public RegionObject() {
		this.regions = new ArrayList<String>();
	}

	public ArrayList<Region> getRegions() {
		RegionList theRegionList = RegionList.getInstance();
		ArrayList<Region> theListOfRegions = new ArrayList<Region>();

		for (String regionId : regions) {
			Region theRegion = theRegionList.get(regionId);
			theListOfRegions.add(theRegion);
		}

		return theListOfRegions;
	}

	public void addRegion(Region region) {
		this.regions.add(region.getId());
	}

	public void removeRegion(Region region) {
		this.regions.remove(region.getId());
	}

	public void removeAllRegions() {
		this.regions.clear();
	}

}
