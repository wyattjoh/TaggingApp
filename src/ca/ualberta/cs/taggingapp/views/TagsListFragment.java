package ca.ualberta.cs.taggingapp.views;

<<<<<<< HEAD:src/com/example/taggingapp/View/TagsListFragment.java

import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
=======
import ca.ualberta.cs.taggingapp.R;
>>>>>>> 7d3824e93a37eeacb4fec3b102317be2d75683ea:src/ca/ualberta/cs/taggingapp/views/TagsListFragment.java
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.taggingapp.R;
 
public class TagsListFragment extends Fragment {
	
	private ArrayList <String> tags = new ArrayList<String>();	
	private Adapter adapter;
	private SearchView searchView;
	ListView listView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
    	Random randomGenerator = new Random();
        for (int i = 0; i < 25; i++){
          int randomInt = randomGenerator.nextInt(10000);
          tags.add("#" + Integer.toString(randomInt));
        }
    	
        final View rootView = inflater.inflate(R.layout.fragment_tags_list, container, false);
        listView = (ListView) rootView.findViewById(R.id.tags_list_view);
        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                R.layout.list_item, tags);
        
        listView.setAdapter(adapter); 
        
        listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent,
					View v, int position, long id)
			{
				Intent i = new Intent(rootView.getContext(), TagRefinedImages.class);
				i.putExtra("tagName", adapter.getItem(position));
				startActivity(i);
			}
		});
      
        return rootView;
    }
}