package ca.ualberta.cs.taggingapp.views;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.ActiveUserModel;
import ca.ualberta.cs.taggingapp.models.ImageLoadingFactory;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.RegionList;
import ca.ualberta.cs.taggingapp.models.TagList;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Tagging App");

		createSharedSingletons(getApplicationContext());

		// Check if logged in already
		if (ActiveUserModel.getShared().isLoggedIn()) {
			continueToSwipeSuper();
		}
	}

	protected void createSharedSingletons(Context theContext) {
		// Create the picture and tags list
		PictureList.createInstance(theContext);
		TagList.createInstance(theContext);
		RegionList.createInstance(theContext);

		// Create the active user model
		ActiveUserModel.createShared(theContext);

		// Create the ImageLoadingFactory
		ImageLoadingFactory.createInstance(theContext);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loginGuest(View view) {
		// Create the user
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(0, 10);
		ActiveUserModel.getShared().performLogin(
				"guest-" + uuid + "@example.com", "password");

		Intent i = new Intent(MainActivity.this, SwipeSuper.class);
		startActivity(i);
		MainActivity.this.finish();
	}

	public void loginUser(View view) {
		if (this.verifyLoginDetails()) {
			continueToSwipeSuper();
		} else {
			String s = "Invalid Username/Password";
			Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
		}
	}

	private void continueToSwipeSuper() {
		Intent i = new Intent(MainActivity.this, SwipeSuper.class);
		startActivity(i);
		finish();
	}

	public void signUp(View view) {
		EditText emailField = (EditText) findViewById(R.id.email);

		Intent i = new Intent(MainActivity.this, SignUp.class);

		String emailString = emailField.getText().toString();
		if (emailString.length() > 0) {
			i.putExtra(SignUp.EMAIL_FIELD_EXTRA_KEY, emailString);
		}

		startActivity(i);
	}

	public boolean verifyLoginDetails() {
		// this method should verify if the email and password are a valid
		// combination
		String email = ((EditText) findViewById(R.id.email)).getText()
				.toString();
		String password = ((EditText) findViewById(R.id.password)).getText()
				.toString();

		return ActiveUserModel.getShared().performLogin(email, password);
	}

}
