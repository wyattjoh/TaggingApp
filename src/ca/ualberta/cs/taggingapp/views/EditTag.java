package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

/**
 * @author Tagging Group Activity that allows the user to modify the name and
 *         URL of a given tag. The user is brought here after selecting 'edit
 *         tag' from the "TagAndPhoto" activity.
 * 
 */
public class EditTag extends Activity {
	public static final String TAG_ID = "TAG_ID";

	private Tag theTag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tag);
		setTitle("TaggingApp");

		// Get the tag name and photo id
		Bundle extras = getIntent().getExtras();

		String tagId = extras.getString(TAG_ID);

		theTag = TagList.getInstance().get(tagId);

		// Set the tag url
		EditText urlField = (EditText) findViewById(R.id.url_edit);
		urlField.setText(theTag.getURL());

		// Append the tagName to the tag text input field.
		EditText tagNameField = (EditText) findViewById(R.id.tag_edit);
		tagNameField.setText(theTag.getName());

	}

	// Set the right menu options
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
								// TODO: Implement
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

		// create alert dialog and show it
		alertDialogBuilder.create().show();
	}

	// Saves the new information to the correct tag instances and saves the
	// altered PictureList to SD card.
	public void saveTag(View view) {
		// Get the urlField
		EditText urlField = (EditText) findViewById(R.id.url_edit);

		// Get the tagNameField
		EditText tagNameField = (EditText) findViewById(R.id.tag_edit);

		// Update the tag object
		theTag.setName(tagNameField.getText().toString());
		theTag.setURL(urlField.getText().toString());

		// Save the list as we just updated a tag
		TagList.getInstance().save();

		// Finish the activity
		finish();
	}

	public void delete() {
		// TODO: Implement
	}

}
