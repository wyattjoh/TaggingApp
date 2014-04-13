package ca.ualberta.cs.taggingapp.views;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		theContext = theActivity.getApplicationContext();

		thePictureList = PictureList.getInstance().getPictureList();
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

		try {
			imageView.setImageBitmap(thePictue.getPicture());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(160, 160));
		imageView.setTag(position);

		return imageView;
	}

}