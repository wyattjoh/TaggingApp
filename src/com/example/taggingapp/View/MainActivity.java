package com.example.taggingapp.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taggingapp.R;

public class MainActivity extends Activity {
	
	String VALID_USER = "guest";
	String VALID_PASS = "1234";
	
	String	 	email,
				password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void loginGuest(View view) {
		Intent i = new Intent(MainActivity.this, PhotoGrid.class);
		startActivity(i);
	}
	
	public void loginUser(View view) {
		if (this.verify()) {
			Intent i = new Intent(MainActivity.this, PhotoGrid.class);
			startActivity(i);
		}
		else {
			String s = "Invalid Username/Password";
			Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
		}
	}
	
	public void signUp(View view) {
		String s = "Login as Guest";
		Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
	}
	
	public boolean verify() {
		
		// this method should verify if the email and password are a valid combination
		email = ((EditText) findViewById(R.id.email)).getText().toString();
		password = ((EditText) findViewById(R.id.password)).getText().toString();
		
		if (email.equals(VALID_USER) && password.equals(VALID_PASS)) {
			return true;
		}
		
		return false;
	}
	
}
