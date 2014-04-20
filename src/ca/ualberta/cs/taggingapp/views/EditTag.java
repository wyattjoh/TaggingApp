package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.RegionList;
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
								RegionList.getInstance().deleteAllRegionsForTag(theTag);
								TagList.getInstance().remove(theTag);
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

		// create alert dialog and show it
		alertDialogBuilder.create().show();
	}

	// Saves the new information to the correct tag instances and saves the
	// altered PictureList to SD card.
	public void saveTag(View view) {
		// Get the urlField
		EditText urlField = (EditText) findViewById(R.id.url_edit);
		String urlFieldString = urlField.getText().toString();

		// Get the tagNameField
		EditText tagNameField = (EditText) findViewById(R.id.tag_edit);
		String nameFieldString = tagNameField.getText().toString();

		if (urlFieldString.length() <= 0) {
			Toast.makeText(getApplicationContext(),
					"Can't have an empty tag url!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		
		if (nameFieldString.length() <= 0) {
			Toast.makeText(getApplicationContext(),
					"Can't have an empty tag name!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		
		// Update the tag object
		theTag.setName(nameFieldString);
		theTag.setURL(urlFieldString);

		// Save the list as we just updated a tag
		TagList.getInstance().save();

		// Finish the activity
		finish();
	}

	public void delete() {
		// TODO: Implement
	}

}
