package com.example.taggingapp.View;

import com.example.taggingapp.R;
import com.example.taggingapp.R.layout;
import com.example.taggingapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SignUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}
