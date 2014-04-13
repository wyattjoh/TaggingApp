package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.UserController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {
	public static final String EMAIL_FIELD_EXTRA_KEY = "emailField";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		Intent startingIntent = getIntent();

		Bundle extras = startingIntent.getExtras();

		if (extras != null) {
			String emailString = extras.getString(EMAIL_FIELD_EXTRA_KEY);
			populateView(emailString);
		}
	}

	/*
	 * Adds in the email text if they have entered it
	 */
	private void populateView(String emailString) {
		EditText emailField = (EditText) findViewById(R.id.email);
		emailField.setText(emailString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	public void createUser(View view) {
		EditText emailText = (EditText) findViewById(R.id.email);
		String emailString = emailText.getText().toString();

		EditText passwordText = (EditText) findViewById(R.id.password);
		String passwordString = passwordText.getText().toString();

		EditText confirmPasswordText = (EditText) findViewById(R.id.confirmPass);
		String confirmPasswordString = confirmPasswordText.getText().toString();

		if (UserController.signUpUser(emailString, passwordString,
				confirmPasswordString)) {
			Intent i = new Intent(SignUp.this, SwipeSuper.class);
			startActivity(i);
			SignUp.this.finish();
		} else {
			String s = "Password did not match";
			Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();

			// Clear the eneterd passwords
			passwordText.setText("");
			confirmPasswordText.setText("");
		}
	}

}
