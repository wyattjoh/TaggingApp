package ca.ualberta.cs.taggingapp.models;

import java.util.UUID;

/**
 * A object with an Id
 * 
 * @author wyatt
 * 
 */
public class ManagedObject {

	protected String id;

	public ManagedObject() {
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
