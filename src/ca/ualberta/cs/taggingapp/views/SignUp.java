package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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

	public void createUser(View view) {
		Intent i = new Intent(SignUp.this, SwipeSuper.class);
		startActivity(i);
		SignUp.this.finish();
	}
	
}
