package ca.ualberta.cs.taggingapp.views;

import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.controllers.UserController;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SwipeSuper extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager vp;
	private TabsPagerAdapter tpa;
	private ActionBar ab;

	String[] tabNames = { "Photos", "Tags" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_super);
		setTitle("Tagging App");

		// Intiialize all of the variables to the XML indentifiers
		vp = (ViewPager) findViewById(R.id.pager);
		ab = getActionBar();
		tpa = new TabsPagerAdapter(getSupportFragmentManager());

		vp.setAdapter(tpa);
		// ab.setHomeButtonEnabled(false); **** NOTE: THIS SHOULD BE
		// UN-COMMENTED LATER ****
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Initialize the tabs from the
		for (String tab : tabNames) {
			ab.addTab(ab.newTab().setText(tab).setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				ab.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.swipe_super_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.camera:
			// the openCamera() method calls katherines code
			// openCamera();
			Intent i = new Intent(SwipeSuper.this, CameraAndPhoto.class);
			startActivity(i);
			//finish();
			return true;
		case R.id.logout:
			logout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Show the right fragment when the tab is selected
		vp.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	private void logout() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder.setTitle("Logout?");

		// set dialog message
		alertDialogBuilder
				.setMessage("Are you sure you want to logout?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// Logout the current user
								UserController.logoutUser();

								// if this button is clicked, close
								// current activity
								Intent i = new Intent(SwipeSuper.this,
										MainActivity.class);
								startActivity(i);
								SwipeSuper.this.finish();
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

}
