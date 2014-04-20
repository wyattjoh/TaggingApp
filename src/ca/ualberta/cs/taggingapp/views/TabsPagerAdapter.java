package ca.ualberta.cs.taggingapp.views;

//import com.example.swipeviewtest.GridView;
//import com.example.swipeviewtest.TagsList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author Tagging Group Returns the proper fragment classes for each tab.
 * 
 */
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
			// break;
		case 1:
			return new TagsListFragment();
			// break;
		}

		return null;

	}

	/* Return the count: always 2 */
	@Override
	public int getCount() {
		return 2;
	}

}
