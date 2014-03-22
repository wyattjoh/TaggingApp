package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
 
public class GridImageAdapter extends BaseAdapter {
	private ArrayList<Picture> thePictureList;
	private Context theContext;
 
	public GridImageAdapter(Activity theActivity) {
		thePictureList = PictureList.getInstance().getPictureList();
		theContext = theActivity.getApplicationContext();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return thePictureList.size();
	}

	@Override
	public Picture getItem(int arg0) {
		// TODO Auto-generated method stub
		return thePictureList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(theContext);
        Picture thePictue = getItem(position);
        
        imageView.setImageBitmap(thePictue.getPicture());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(160, 160));
        
        return imageView;
    }
 
}