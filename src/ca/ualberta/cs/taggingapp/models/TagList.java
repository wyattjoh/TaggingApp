package ca.ualberta.cs.taggingapp.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import android.content.Context;

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
		this.tagList.add(0, tag);
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
		Gson gson = new Gson();
		
		Tag[] dataToSave = this.tagList.toArray(new Tag[0]);

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
	}

	private void load() {
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
	}
}
