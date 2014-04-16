package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.PictureList;

public class EditTag extends Activity {

	String tagName;
	int position;
	EditText tag;
	EditText url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tag);
		setTitle("Tagging App");

		// Get the tag name and photo id
		Bundle extras = getIntent().getExtras();
		tagName = extras.getString("tagName");
		position = extras.getInt("pos");

		tag = (EditText) findViewById(R.id.tag_edit);
		tag.append(tagName);

		url = (EditText) findViewById(R.id.url_edit);
		url.append("https://stackoverflow.com");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_tag, menu);
		return true;
	}

	public void deleteTag(View view) {

		// The following block of code creates the dialogue box to confirm the
		// delete
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Delete Tag?");

		// set dialog message
		alertDialogBuilder
				.setMessage(
						"Deleting this tag also will delete its references to any photos.")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
								Intent i = new Intent(EditTag.this,
										ViewFullPic.class);
								i.putExtra("tagName", tagName);
								startActivity(i);
								EditTag.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
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

		// Horrible stringy code, forgive me
		PictureList.getInstance().getSelected().getRegions().get(position)
				.getTag().setName(tag.getText().toString());
		PictureList.getInstance().getSelected().getRegions().get(position)
				.getTag().setURL(url.getText().toString());
		EditTag.this.finish();
	}

}
