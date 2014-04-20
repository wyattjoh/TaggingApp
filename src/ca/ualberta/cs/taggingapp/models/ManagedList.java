package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

/**
 * A smart object list for sorting elements
 * 
 * @author wyatt
 * 
 * @param <T>
 */
public class ManagedList<T extends ManagedObject> {

	protected ArrayList<T> theList;

	public ManagedList() {
		this.theList = new ArrayList<T>();
	}

	/**
	 * @param theId
	 * @return the object of Id
	 */
	public T get(String theId) {
		for (T object : this.theList) {
			if (object.getId().equals(theId)) {
				return object;
			}
		}
		return null;
	}

	/**
	 * Gets the element at a position
	 * 
	 * @param position
	 * @return
	 */
	public T get(int position) {
		return this.theList.get(position);
	}

	/**
	 * @return the managed list
	 */
	public ArrayList<T> getArrayList() {
		return theList;
	}

	/**
	 * Sets the list to the new list
	 * 
	 * @param theNewList
	 */
	public void setArrayList(ArrayList<T> theNewList) {
		this.theList.clear();
		this.theList.addAll(theNewList);
	}

	/**
	 * Adds the object to the list
	 * 
	 * @param object
	 */
	public void add(T object) {
		this.theList.add(object);
	}

	/**
	 * Removes the object from the list
	 * 
	 * @param object
	 */
	public void remove(T object) {
		this.theList.remove(object);
	}

}
