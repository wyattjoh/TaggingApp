package ca.ualberta.cs.taggingapp.models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;

import com.google.gson.Gson;

public class TagList {
	private final static String FILENAME = "TagList.json"; 
	private static TagList singleton = null;
	private ArrayList<Tag> tagList;
	private Tag selectedTag = null;
	private Context theContext;

	private TagList() {
		this.tagList = new ArrayList<Tag>();
	}

	public void setTheContext(Context theContext) {
		this.theContext = theContext;
	}

	public Context getTheContext() {
		return this.theContext;
	}

	public static TagList createInstance(Context theContext) {
		singleton = new TagList();
		singleton.setTheContext(theContext);
		return singleton;
	}

	public static TagList getInstance() {
		if (singleton == null) {
			throw new RuntimeException("Cannot get shared of TagList when none created!");
		}
		return singleton;
	}

	public ArrayList<Tag> getTags() {
		return tagList;
	}

	//These methods add or remove tags from the TagList
	public void addTag(Tag tag) {
		this.tagList.add(tag);
		//save();
	}

	public void removeTag(Tag tag) {
		this.tagList.remove(tag);

		//save();
	}

	public Tag getTag(int position) {
		return this.tagList.get(position);
	}

	public void setSelected(int position) {
		selectedTag = this.tagList.get(position);
	}

	public Tag getSelected() {
		return selectedTag;
	}

	//This method allows for searching the TagList
	public Tag findTag(String tagName) {
		Tag foundTag = null;

		Tag searchTag = new Tag(tagName);


		for (Tag tag : this.tagList) {
			if (tag.equals(searchTag)) {
				foundTag = tag;
				break;
			}
		}

		return foundTag;
	}

	private void save() {

		// New Saving //

		Tag[] dataToSave = new Tag[tagList.size()];

		System.out.println("********************************" + tagList.size());
		for (int i = 0; i < tagList.size(); i++) {
			dataToSave[i] = this.tagList.get(i);
		}

		Gson gson = new Gson();
		String s = gson.toJson(dataToSave[0]);
		try {
			FileOutputStream outputStream;


			outputStream = theContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			outputStream.write(s.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		Gson gson = new Gson();

		//Tag[] dataToSave = this.tagList.toArray(new Tag[0]);
		Tag[] dataToSave = new Tag[tagList.size()];
		for (int i = 0; i < tagList.size(); i++) {
			dataToSave[i] = this.tagList.get(i);
		}

		try {
			FileOutputStream fos = theContext.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			gson.toJson(dataToSave, osw);

			osw.close();
			fos.close();

		} catch (FileNotFoundException e) {
			System.err.println("FILE WASN'T FOUND");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO ERROR");
			e.printStackTrace();
		}
		 */
	}

	private void load() {

		// New Load //
		try {
			FileInputStream fis = theContext.openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}

			String json = sb.toString();
			Gson gson = new Gson();
			Tag[] dataToSave = gson.fromJson(json, Tag[].class);

		} catch (Exception e) {
			// File was not found! Create it!
			save();
		}
		/*
		Gson gson = new Gson();
		ArrayList<Tag> dataThatLoaded = new ArrayList<Tag>();

		try {
			FileInputStream fis = theContext.openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);

			Tag[] thePrimative = gson.fromJson(isr, Tag[].class);

			isr.close();
			fis.close();

			for(int i = 0; i < thePrimative.length; i++) {
				dataThatLoaded.add(thePrimative[i]);
			}


		} catch (FileNotFoundException e) {
			// File was not found! Create it!
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
	}
}
