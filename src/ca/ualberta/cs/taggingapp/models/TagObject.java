package ca.ualberta.cs.taggingapp.models;

public class TagObject {
	/**
	 * 
	 */
	private int thePosition;
	private Picture thePicture;
	
	public TagObject(int position, Picture picture) {
		thePosition = position;
		thePicture = picture;
	}

	/**
	 * @return the thePosition
	 */
	public int getThePosition() {
		return thePosition;
	}

	/**
	 * @param thePosition the thePosition to set
	 */
	public void setThePosition(int thePosition) {
		this.thePosition = thePosition;
	}

	/**
	 * @return the thePicture
	 */
	public Picture getThePicture() {
		return thePicture;
	}

	/**
	 * @param thePicture the thePicture to set
	 */
	public void setThePicture(Picture thePicture) {
		this.thePicture = thePicture;
	}
}