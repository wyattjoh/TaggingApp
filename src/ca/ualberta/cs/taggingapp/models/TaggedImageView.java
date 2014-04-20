package ca.ualberta.cs.taggingapp.models;

import java.util.ArrayList;

import android.content.Context;
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
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
	}

	public void setPicture(Picture picture) {
		this.picture = picture;

		this.picture.setLargeBitmapOnImageView(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		ArrayList<Region> theRegions = this.picture.getRegions();

		paint.setColor(Color.rgb(232, 232, 232));
		for (Region theRegion : theRegions) {
			paint.setAlpha(60);
			paint.setStyle(Style.FILL);
			canvas.drawRect(theRegion.getUpperLeftCorner().x,
					theRegion.getUpperLeftCorner().y,
					theRegion.getLowerRightCorner().x,
					theRegion.getLowerRightCorner().y, paint);

			paint.setStyle(Style.STROKE);
			paint.setAlpha(255);
			canvas.drawRect(theRegion.getUpperLeftCorner().x,
					theRegion.getUpperLeftCorner().y,
					theRegion.getLowerRightCorner().x,
					theRegion.getLowerRightCorner().y, paint);
		}

		paint.setColor(Color.WHITE);
		paint.setAlpha(255);
		for (int i = 0; i < theRegions.size(); i++) {
			Region theRegion = theRegions.get(i);

			paint.setTextSize(20);
			canvas.drawText(Integer.toString(i + 1),
					theRegion.getUpperLeftCorner().x + 5,
					theRegion.getUpperLeftCorner().y + 20, paint);
		}
	}
}