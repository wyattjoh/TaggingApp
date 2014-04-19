package ca.ualberta.cs.taggingapp.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Associated with full_view_pic. Draws the selected picture on the canvas.
 * 
 * */

public class TaggedImageView extends ImageView {

	private Paint paint;
	private Picture picture;

	public TaggedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
	}

	public void setPicture(Picture picture) {
		this.picture = picture;

		Bitmap theBitmap;

		try {
			theBitmap = picture.getPicture();

			this.setImageBitmap(theBitmap);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		ArrayList<Region> theRegions = this.picture.getRegions();

		for (int i = 0; i < theRegions.size(); i++) {
			Region theRegion = theRegions.get(i);
			
			canvas.drawRect(theRegion.getUpperLeftCorner().x,
					theRegion.getUpperLeftCorner().y,
					theRegion.getLowerRightCorner().x,
					theRegion.getLowerRightCorner().y, paint);
			
			paint.setTextSize(20);
			canvas.drawText(Integer.toString(i + 1), theRegion.getUpperLeftCorner().x + 5, theRegion.getUpperLeftCorner().y + 20, paint); 
		}
	}
}