package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.Region;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagList;

public class EditTag extends Activity {

	String tagName;
	int position;
	EditText tag;
	EditText url;
	Picture thePicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tag);
		setTitle("Tagging App");
		
		thePicture = PictureList.getInstance().getSelected();

		// Get the tag name and photo id
		Bundle extras = getIntent().getExtras();
		tagName = extras.getString("tagName");
		
		url = (EditText) findViewById(R.id.url_edit);
		ArrayList <Region> regs = PictureList.getInstance().getSelected().getRegions();
		for (int i = 0; i < regs.size(); i++) {
			if (regs.get(i).getTag().getName().equals(tagName)) {
				url.append(regs.get(i).getTag().getURL());
			}
		}
		position = extras.getInt("pos");

		tag = (EditText) findViewById(R.id.tag_edit);
		tag.append(tagName);

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
								delete();
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
		TagList.getInstance().save();
		PictureList.getInstance().save();
		EditTag.this.finish();
	}
	
	public void delete() {
		
		// Delete all instances of the tag from all pictures
		ArrayList<Picture> pics = PictureList.getInstance().getPictureList();
		for (int i = 0; i < pics.size(); i++) {
			ArrayList<Region> regs = pics.get(i).getRegions();
			for (int j = 0; j < regs.size(); j++) {
				if (regs.get(j).getTag().getName().equals(tagName)) {
					PictureList.getInstance().getPicture(i).removeRegion(regs.get(j));
				}
			}
		}
		
		// Delete the tag from the tag list
		ArrayList<Tag> tags = TagList.getInstance().getTags();
		for (int k = 0; k < tags.size(); k++) {
			if (tags.get(k).getName().equals(tagName)) {
				TagList.getInstance().removeTag(tags.get(k));
			}
		}
		
		//TagList.getInstance().save();
		//PictureList.getInstance().save();
	}

}
