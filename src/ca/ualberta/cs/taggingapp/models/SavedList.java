package ca.ualberta.cs.taggingapp.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Stores information relating to application to the disc.
 * 
 * */

public abstract class SavedList<T extends ManagedObject> extends ManagedList<T> {

	private Context theContext;

	public SavedList(Context theContext) {
		this.theContext = theContext;
	}

	/**
	 * @return the filename to save the data as
	 */
	public abstract String getFilename();

	/**
	 * @param orignalArray
	 * @return the primative array for the arraylist
	 */
	public abstract T[] getPrimativeArray(ArrayList<T> orignalArray);

	/**
	 * @return the type for gson
	 */
	public abstract Type getType();

	/**
	 * Saves the current list to the filesystem
	 */
	public void save() {
		try {
			Gson gson = new Gson();

			FileOutputStream fos = theContext.openFileOutput(getFilename(),
					Context.MODE_PRIVATE);
			fos.getChannel().lock();
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			T[] theStateList = getPrimativeArray(theList);
			gson.toJson(theStateList, osw);

			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
	}

	/**
	 * Removes the current list from the filesystem
	 */
	public void remove() {
		this.theList.clear();
		save();
	}

	/**
	 * @return the loaded list from the filesystem
	 */
	protected ArrayList<T> load() {
		ArrayList<T> theState = new ArrayList<T>();

		try {
			Gson gson = new Gson();

			FileInputStream fis = theContext.openFileInput(getFilename());
			InputStreamReader isr = new InputStreamReader(fis);

			Type listofTObject = getType();
			T[] theStateList = gson.fromJson(isr, listofTObject);

			if (theStateList != null) {
				for (T object : theStateList) {
					theState.add(object);
				}
			}

			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return theState;
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.taggingapp.models.ManagedList#setArrayList(java.util.ArrayList)
	 */
	@Override
	public void setArrayList(ArrayList<T> theNewList) {
		super.setArrayList(theNewList);
		save();
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.taggingapp.models.ManagedList#add(ca.ualberta.cs.taggingapp.models.ManagedObject)
	 */
	@Override
	public void add(T object) {
		super.add(object);
		save();
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.taggingapp.models.ManagedList#remove(ca.ualberta.cs.taggingapp.models.ManagedObject)
	 */
	@Override
	public void remove(T object) {
		super.remove(object);
		save();
	}
}
