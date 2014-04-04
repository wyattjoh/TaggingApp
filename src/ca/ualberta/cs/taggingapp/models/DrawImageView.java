package ca.ualberta.cs.taggingapp.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DrawImageView extends ImageView {
	
	private Paint paint;
    private PointF startPoint, endPoint;
    private boolean isDrawing;

    public DrawImageView(Context context, AttributeSet attrs)
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
    
    public PointF getUpperLeftPoint() {
    	return startPoint;
    }
    
    public PointF getLowerRightPoint() {
    	return endPoint;
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        if(isDrawing)
        {
            canvas.drawRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startPoint = new PointF(event.getX(), event.getY());
                endPoint = new PointF();
                isDrawing = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDrawing) {
                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDrawing) {
                    endPoint.x = event.getX();
                    endPoint.y = event.getY();
                    invalidate();
                    if (endPoint.x < startPoint.x) {
                    	float temp = endPoint.x;
                    	endPoint.x = startPoint.x;
                    	startPoint.x = temp;
                    }
                    if (endPoint.y < startPoint.y) {
                    	float temp = endPoint.y;
                    	endPoint.y = startPoint.y;
                    	startPoint.y = temp;
                    }
                }
                isDrawing = true;
                break;
            default:
                break;
        }
        return true;
    }
	
	@Override
	public void setImageBitmap(Bitmap picture) {
		super.setImageBitmap(picture);
	}
}