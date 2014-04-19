package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class ManagedList<T extends ManagedObject> {

	protected ArrayList<T> theList;

	public ManagedList() {
		this.theList = new ArrayList<T>();
	}

	public T get(String theId) {
		for (T object : this.theList) {
			if (object.getId().equals(theId)) {
				return object;
			}
		}
		return null;
	}

	public ArrayList<T> getArrayList() {
		return theList;
	}

	public void setArrayList(ArrayList<T> theNewList) {
		this.theList.clear();
		this.theList.addAll(theNewList);
	}

	public void add(T object) {
		this.theList.add(object);
	}

	public void remove(T object) {
		this.theList.remove(object);
	}

	public T get(int position) {
		return this.theList.get(position);
	}

}
