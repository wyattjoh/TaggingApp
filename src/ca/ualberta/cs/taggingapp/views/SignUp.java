package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.UserController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Tagging Gtroup Activity that allows the user to create a new profile.
 *         The user is taken here from mainActivity after selecting 'sign up'.
 * 
 */
public class SignUp extends Activity {
	public static final String EMAIL_FIELD_EXTRA_KEY = "emailField";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		setTitle("TaggingApp SignUp");

		// Get the email that was entered in the previous screen
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

	/*
	 * Create the user from the email and password that they entered.
	 */
	public void createUser(View view) {
		// Get email and pass from text fields
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
