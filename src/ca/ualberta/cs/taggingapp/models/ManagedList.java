package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

public class ManagedList<T extends ManagedObject> {

	protected ArrayList<T> theList;

	public ManagedList() {
		// TODO Auto-generated constructor stub
	}

	public T get(String theId) {
		for (T object : this.theList) {
			if (object.getId().equals(theId)) {
				return object;
			}
		}
		return null;
	}

}
