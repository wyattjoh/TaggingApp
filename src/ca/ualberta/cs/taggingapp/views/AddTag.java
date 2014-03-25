package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.DrawImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;

public class AddTag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tag);
		setTitle("Tagging App");
		Picture thePicture = PictureList.getInstance().getSelected();
		DrawImageView picture = (DrawImageView) findViewById(R.id.drawImageView1);
		picture.setBackground(new BitmapDrawable(getResources(), thePicture.getPicture()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_tag, menu);

		return true;
	}

	@Override
	public void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}
}
