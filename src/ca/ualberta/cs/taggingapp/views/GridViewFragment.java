package ca.ualberta.cs.taggingapp.views;

<<<<<<< HEAD:src/com/example/taggingapp/View/GridViewFragment.java
import android.content.Intent;
=======
import ca.ualberta.cs.taggingapp.R;
>>>>>>> 7d3824e93a37eeacb4fec3b102317be2d75683ea:src/ca/ualberta/cs/taggingapp/views/GridViewFragment.java
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
<<<<<<< HEAD:src/com/example/taggingapp/View/GridViewFragment.java

import com.example.taggingapp.R;

=======
 
>>>>>>> 7d3824e93a37eeacb4fec3b102317be2d75683ea:src/ca/ualberta/cs/taggingapp/views/GridViewFragment.java
public class GridViewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.grid_layout, container, false);
		GridView gridView = (GridView) rootView.findViewById(R.id.photo_grid_view);

		// Instance of ImageAdapter Class
		final GridImageAdapter gia = new GridImageAdapter(rootView.getContext());
		gridView.setAdapter(gia);

		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent,
					View v, int position, long id)
			{
				Intent i = new Intent(rootView.getContext(), ViewFullPic.class);
				i.putExtra("imageName", (Integer)gia.getItem(position));
				startActivity(i);
				/*
				Toast.makeText(rootView.getContext(),
						"pic" + (gia.getItem(position)) +" selected",
						Toast.LENGTH_SHORT).show();
				 */
			}
		});

		return rootView;
	}




}

