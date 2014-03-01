package com.example.taggingapp.View;

import com.example.taggingapp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 
public class GridImageAdapter extends BaseAdapter {
    
	private Context con;
 
    // Keep all Images in array
    public Integer[] picIDs = {
            R.drawable.ex_pic1,
            R.drawable.ex_pic2,
            R.drawable.ex_pic5,
            R.drawable.ex_pic2,
            R.drawable.ex_pic3,
            R.drawable.ex_pic2,
            R.drawable.ex_pic1,
            R.drawable.ex_pic3,
            R.drawable.ex_pic5,
            R.drawable.ex_pic2,
            R.drawable.ex_pic1,
            R.drawable.ex_pic2,
            R.drawable.ex_pic1,
            R.drawable.ex_pic5,
            R.drawable.ex_pic3,
            R.drawable.ex_pic2,
            R.drawable.ex_pic5,
            R.drawable.ex_pic2,
            R.drawable.ex_pic3,
            R.drawable.ex_pic2,
            R.drawable.ex_pic1,
            R.drawable.ex_pic2,
            R.drawable.ex_pic5,
            R.drawable.ex_pic3
    };
 
    // Constructor
    public GridImageAdapter(Context c){
        con = c;
    }
 
    @Override
    public int getCount() {
        return picIDs.length;
    }
 
    @Override
    public Object getItem(int position) {
        return picIDs[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(con);
        imageView.setImageResource(picIDs[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(160, 160));
        return imageView;
    }
 
}