package com.example.taggingapp.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taggingapp.R;

public class EditTag extends Activity {

	String tagName;
	Integer picId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tag);
		setTitle("Tagging App");

		// Get the tag name and photo id
		Bundle extras = getIntent().getExtras();
		picId = (Integer) extras.get("imageName");
		tagName = extras.getString("tagName");

		EditText tag = (EditText) findViewById(R.id.tag_edit);
		tag.append(tagName);

		EditText url = (EditText) findViewById(R.id.url_edit);
		url.append("https://stackoverflow.com");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_tag, menu);
		return true;
	}

	public void deleteTag(View view) {
		
		// The following block of code creates the dialogue box to confirm the delete
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		// set title
		alertDialogBuilder.setTitle("Delete Tag?");

		// set dialog message
		alertDialogBuilder
		.setMessage("Deleting this tag also will delete its references to any photos.")
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity
				Intent i = new Intent(EditTag.this, ViewFullPic.class);
				i.putExtra("tagName", tagName);
				i.putExtra("imageName", picId);
				startActivity(i);
				EditTag.this.finish();
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.cancel();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	public void saveTag(View view) {
		EditTag.this.finish();
	}

}
