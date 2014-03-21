package ca.ualberta.cs.taggingapp.views;

//import com.example.swipeviewtest.GridView;
//import com.example.swipeviewtest.TagsList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	
	/* Default constructor calls the superclass */
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		case 0:
			return new GridViewFragment();
			//break;
		case 1:
			return new TagsListFragment();
			//break;
		}
		
		return null;
				
	}

	/* Return the count: always 2 */
	public int getCount() {
		return 2;
	}

}
