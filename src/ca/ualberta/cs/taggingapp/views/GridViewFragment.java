package ca.ualberta.cs.taggingapp.views;

import android.content.Intent;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.ApplicationState;
import ca.ualberta.cs.taggingapp.models.PictureList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridViewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//ApplicationState.getInstance().load();

		final View rootView = inflater.inflate(R.layout.grid_layout, container,
				false);
		final GridView gridView = (GridView) rootView
				.findViewById(R.id.photo_grid_view);

		// Instance of ImageAdapter Class
		final GridImageAdapter gia = new GridImageAdapter(getActivity());
		gridView.setAdapter(gia);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent i = new Intent(rootView.getContext(), ViewFullPic.class);

				Integer imagePosition = (Integer) v.getTag();

				PictureList.getInstance().setSelected(imagePosition);

				startActivity(i);

				/*
				 * Toast.makeText(rootView.getContext(), "pic" +
				 * (gia.getItem(position)) +" selected",
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});

		return rootView;
	}

}
