package ca.ualberta.cs.taggingapp.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Draws a canvas and provides code for the scaling of bitmaps being displayed
 * on the canvas.
 * 
 * */

public class DrawImageView extends ImageView {

	private Paint paint;
	private PointF startPoint = null;
	private PointF endPoint = null;
	private boolean isDrawing;

	public DrawImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
	}

	/**
	 * @return the upper left point
	 */
	public Point getUpperLeftPoint() {
		int x = Math.round(startPoint.x);
		int y = Math.round(startPoint.y);
		Point point = new Point(x, y);
		return point;
	}

	/**
	 * @return the lower right point
	 */
	public Point getLowerRightPoint() {
		int x = Math.round(endPoint.x);
		int y = Math.round(endPoint.y);
		Point point = new Point(x, y);
		return point;
	}

	/* (non-Javadoc)
	 * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isDrawing) {
			canvas.drawRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y,
					paint);
		}
	}

	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		BoundingBoxSetting boundingBoxSetting = ActiveUserModel.getShared()
				.getUser().getBoundingBoxSetting();

		if (boundingBoxSetting == BoundingBoxSetting.DRAG) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startPoint = new PointF(event.getX(), event.getY());
				endPoint = new PointF();
				isDrawing = true;
				break;

			case MotionEvent.ACTION_MOVE:
				if (isDrawing) {
					endPoint.x = event.getX();
					endPoint.y = event.getY();
				}
				invalidate();
				break;

			case MotionEvent.ACTION_UP:
				if (isDrawing) {
					endPoint.x = event.getX();
					endPoint.y = event.getY();
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
					invalidate();
				}
				isDrawing = true;
				break;

			default:
				break;
			}
		} else if (boundingBoxSetting == BoundingBoxSetting.DOUBLE_TAP) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				if (startPoint == null) {
					startPoint = new PointF(event.getX(), event.getY());
				} else if (endPoint == null) {
					endPoint = new PointF(event.getX(), event.getY());
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
					isDrawing = true;
				} else {
					startPoint.x = event.getX();
					startPoint.y = event.getY();
					endPoint = null;
					isDrawing = false;
				}
				invalidate();
				break;

			default:
				break;
			}
		}

		return true;
	}

	//sets the picture
	public void setPicture(Picture picture) {
		picture.setLargeBitmapOnImageView(this);
	}
}
