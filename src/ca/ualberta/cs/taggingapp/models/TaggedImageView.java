package ca.ualberta.cs.taggingapp.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TaggedImageView extends ImageView {
	
	private Paint paint;

    public TaggedImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
    }

    public void draw(Canvas canvas, Picture picture)
    {
        if(!picture.getRegions().isEmpty())
        {
        	for(Region region:picture.getRegions()) {
        		canvas.drawRect(region.getUpperLeftCorner().x, region.getUpperLeftCorner().y,
        				region.getLowerRightCorner().x, region.getLowerRightCorner().y, paint);
        	}
        }
        invalidate();
    }
	
	@Override
	public void setImageBitmap(Bitmap picture) {
		super.setImageBitmap(picture);
	}
}