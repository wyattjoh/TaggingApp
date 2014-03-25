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
	private Picture picture;

    public TaggedImageView(Context context, AttributeSet attrs, Picture picture)
    {
        super(context, attrs);
        init(picture);
    }

    private void init(Picture picture)
    {
    	this.picture = picture;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        if(!this.picture.getRegions().isEmpty())
        {
        	for(Region region:this.picture.getRegions()) {
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