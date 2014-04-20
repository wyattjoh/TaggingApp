package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.RegionList;
import ca.ualberta.cs.taggingapp.models.Tag;
import ca.ualberta.cs.taggingapp.models.TagObject;

/**
 * @author Tagging Group Simple adapter to map images to the main gridview.
 */
public class GridImageAdapter extends BaseAdapter {
	private ArrayList<Picture> thePictureList;
	private Context theContext;

	public GridImageAdapter(Context theContext, Tag theTag) {
		this.theContext = theContext;

		ArrayList<Picture> pics = PictureList.getInstance().getArrayList();

		if (theTag == null) {
			thePictureList = pics;
		} else {
			thePictureList = RegionList.getInstance().getAllPicturesFromTag(
					theTag);
		}
	}

	@Override
	public int getCount() {
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

	// Returns the image held at position 'position'.
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(theContext);
		Picture thePicture = getItem(position);
		String thePictureId = thePicture.getId();

		TagObject theViewTagObject = (TagObject) imageView.getTag();

		if (theViewTagObject == null) {
			populateImageView(position, imageView, thePicture);
		} else {
			String theTaggedPictureId = theViewTagObject.getThePicture()
					.getId();

			if (!(theTaggedPictureId.equals(thePictureId))) {
				populateImageView(position, imageView, thePicture);
			}
		}

		return imageView;
	}

	protected void populateImageView(int position, ImageView imageView,
			Picture thePicture) {
		TagObject theTagObject = new TagObject(position, thePicture);
		imageView.setTag(theTagObject);
		
		thePicture.setSmallBitmapOnImageView(imageView);

		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(160, 160));
	}

}