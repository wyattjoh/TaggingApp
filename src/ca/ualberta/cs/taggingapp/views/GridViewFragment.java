package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.content.Intent;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.TagObject;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * @author Tagging Group This activity represents the 'Photos' tab in the 'home
 *         screen'. When the left tab is selected, this activity's XML is
 *         displayed below the tab selector.
 * 
 */
public class GridViewFragment extends Fragment {
	private GridImageAdapter gridImageAdapter = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Find the gridview
		final View rootView = inflater.inflate(R.layout.grid_layout, container,
				false);
		final GridView gridView = (GridView) rootView
				.findViewById(R.id.photo_grid_view);

		// Instance of ImageAdapter Class
		gridImageAdapter = new GridImageAdapter(getActivity(), null);
		gridView.setAdapter(gridImageAdapter);

		// When an image is selected, find the correct image and start the new
		// activity
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(rootView.getContext(), ViewFullPic.class);

				TagObject imagePosition = (TagObject) v.getTag();

				PictureList.getInstance().setSelected(
						imagePosition.getThePosition());

				startActivity(i);
			}
		});

		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handles presses on the action bar items
		switch (item.getItemId()) {
		case R.id.camera:
			newPicture();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void newPicture() {
		// calls camera code
		Intent i = new Intent(getActivity(), CameraAndPhoto.class);
		startActivityForResult(i, CameraAndPhoto.REQUEST_TAKE_PHOTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	// Upon return from the camera functions, save the taken photo and show it
	// in the gridview
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CameraAndPhoto.REQUEST_TAKE_PHOTO) {
			if (resultCode == Activity.RESULT_OK) {

				Uri photoUri = Uri.parse(data
						.getStringExtra(CameraAndPhoto.PHOTO_URI));
				PictureList.getInstance().add(photoUri);
				gridImageAdapter.notifyDataSetChanged();

			} else if (resultCode == Activity.RESULT_CANCELED) {

			} else {

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		gridImageAdapter.notifyDataSetChanged();
	}

}
