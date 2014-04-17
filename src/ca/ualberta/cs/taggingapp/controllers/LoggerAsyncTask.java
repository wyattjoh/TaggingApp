package ca.ualberta.cs.taggingapp.controllers;

import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class LoggerAsyncTask extends AsyncTask<LoggerAction, Void, Integer[]> {

	@Override
	protected Integer[] doInBackground(LoggerAction... arg0) {
		int length = arg0.length;
		Integer[] theResponses = new Integer[arg0.length];

		for (int i = 0; i < length; i++) {
			LoggerAction theAction = arg0[i];
			theResponses[i] = performRequest(theAction);
		}

		return theResponses;
	}

	protected Integer performRequest(LoggerAction theAction) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost method = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301w14t12/tags");

		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("timePerformed", Long.toString(new Date().getTime()));
			jsonObject.accumulate("timeTaken", Long.toString(theAction.getTheTimeToFinish()));
			jsonObject.accumulate("methodForDrag", theAction.getTheMethod());
			jsonObject.accumulate("userEmail", theAction.getTheUser());
			
			String json = jsonObject.toString();
			
			StringEntity se = new StringEntity(json);
			
			// 6. set httpPost Entity
			method.setEntity(se);
 
            // 7. Set some headers to inform server about the type of the content   
			method.setHeader("Accept", "application/json");
			method.setHeader("Content-type", "application/json");

			HttpResponse response = httpclient.execute(method);

			return response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			System.err.println("Fatal error: " + e.getMessage());
			e.printStackTrace();
		}

		return 500;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Integer[] result) {
		for (int i = 0; i < result.length; i++) {
			Log.w("LoggerAsyncTask", "The response from logging request was: "
					+ result[i].toString());
		}
	}

}
