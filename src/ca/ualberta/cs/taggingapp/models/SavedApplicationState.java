package ca.ualberta.cs.taggingapp.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import android.content.Context;

public class SavedApplicationState {
	private final String FILENAME = "applicationState.json";
	private Context theContext;

	public SavedApplicationState(Context theContext) {
		this.theContext = theContext;
	}

	public void save(ApplicationState theApplicationState) {
		try {
			Gson gson = new Gson();

			FileOutputStream fos = theContext.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			fos.getChannel().lock();
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			gson.toJson(theApplicationState, osw);

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

	public ApplicationState load() {
		ApplicationState theState = null;

		try {
			Gson gson = new Gson();

			FileInputStream fis = theContext.openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);

			theState = gson.fromJson(isr, ApplicationState.class);

			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

			theState = new ApplicationState();

		} catch (IOException e) {
			e.printStackTrace();

			theState = new ApplicationState();
		}

		return theState;
	}

}
