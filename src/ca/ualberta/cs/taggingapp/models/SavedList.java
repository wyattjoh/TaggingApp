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
 *  Stores information relating to application 
 *  to the disc.
 * 
 * */

public abstract class SavedList<T> {

	private Context theContext;
	protected ArrayList<T> theList;

	public SavedList(Context theContext) {
		this.theContext = theContext;
	}

	public abstract String getFilename();

	public abstract T[] getPrimativeArray(ArrayList<T> orignalArray);

	public abstract Type getType();

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

	public void remove() {
		this.theList = new ArrayList<T>();
		save();
	}

	protected ArrayList<T> load() {
		ArrayList<T> theState = new ArrayList<T>();

		try {
			Gson gson = new Gson();

			FileInputStream fis = theContext.openFileInput(getFilename());
			InputStreamReader isr = new InputStreamReader(fis);

			Type listofTObject = getType();
			T[] theStateList = gson.fromJson(isr, listofTObject);

			for (T object : theStateList) {
				theState.add(object);
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
}
