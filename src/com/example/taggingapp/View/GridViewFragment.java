package com.example.taggingapp.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.taggingapp.R;
 
public class GridViewFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.grid_layout, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.photo_grid_view);
        
     // Instance of ImageAdapter Class
        gridView.setAdapter(new GridImageAdapter(rootView.getContext()));
        
        return rootView;
    }
    
   
    
    
}

