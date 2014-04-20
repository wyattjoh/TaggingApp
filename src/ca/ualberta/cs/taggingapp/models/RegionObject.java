package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

/**
 * An object that contains regions
 * 
 * @author wyatt
 * 
 */
public class RegionObject extends ManagedObject {

	protected ArrayList<String> regions;

	public RegionObject() {
		this.regions = new ArrayList<String>();
	}

	/**
	 * @return all the regions
	 */
	public ArrayList<Region> getRegions() {
		RegionList theRegionList = RegionList.getInstance();
		ArrayList<Region> theListOfRegions = new ArrayList<Region>();

		for (String regionId : regions) {
			Region theRegion = theRegionList.get(regionId);
			theListOfRegions.add(theRegion);
		}

		return theListOfRegions;
	}

	/**
	 * @param region
	 */
	public void addRegion(Region region) {
		this.regions.add(region.getId());
	}

	/**
	 * @param region
	 */
	public void removeRegion(Region region) {
		this.regions.remove(region.getId());
	}

	/**
	 * removes all regions
	 */
	public void removeAllRegions() {
		this.regions.clear();
	}

}
