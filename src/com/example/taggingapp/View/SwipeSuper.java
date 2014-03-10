package com.example.taggingapp.View;

import com.example.taggingapp.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class SwipeSuper extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager vp;
	private TabsPagerAdapter tpa;
	private ActionBar ab;
	
	String [] tabNames = {"Photos", "Tags"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_super);
		
		// Intiialize all of the variables to the XML indentifiers
		vp = (ViewPager) findViewById(R.id.pager);
        ab = getActionBar();
        tpa = new TabsPagerAdapter(getSupportFragmentManager());
        
        vp.setAdapter(tpa);
        //ab.setHomeButtonEnabled(false); **** NOTE: THIS SHOULD BE UN-COMMENTED LATER ****
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // Show the right fragment when the tab is selected
        vp.setCurrentItem(tab.getPosition());
    }

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
