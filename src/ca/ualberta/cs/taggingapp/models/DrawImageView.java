package ca.ualberta.cs.taggingapp.models;

import android.content.Context;
import android.graphics.Bitmap;
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
 * on the canvas. Contains code for pinch gesture recognition.
 * 
 * */

public class DrawImageView extends ImageView {

	private Paint paint;
	private PointF startPoint = null;
	private PointF endPoint = null;
	private boolean isDrawing;

	public DrawImageView(Context context, AttributeSet attrs) {
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

	public Point getUpperLeftPoint() {
		int x = Math.round(startPoint.x);
		int y = Math.round(startPoint.y);
		Point point = new Point(x, y);
		return point;
	}

	public Point getLowerRightPoint() {
		int x = Math.round(endPoint.x);
		int y = Math.round(endPoint.y);
		Point point = new Point(x, y);
		return point;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (isDrawing) {
			canvas.drawRect(startPoint.x, startPoint.y, endPoint.x, endPoint.y,
					paint);
		}
		super.onDraw(canvas);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DRAG") {
				startPoint = new PointF(event.getX(), event.getY());
				endPoint = new PointF();
				isDrawing = true;
			}

		case MotionEvent.ACTION_MOVE:
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DRAG") {
				if (isDrawing) {
					endPoint.x = event.getX();
					endPoint.y = event.getY();
				}
			}

			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DRAG") {
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
			}

			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DEFAULT_TAP") {
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
			}
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
