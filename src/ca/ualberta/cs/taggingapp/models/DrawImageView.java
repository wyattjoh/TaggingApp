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
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 *  Draws a canvas and provides code for the scaling of
 *  bitmaps being displayed on the canvas. Contains code
 *  for pinch gesture recognition.
 * 
 * */

public class DrawImageView extends ImageView {

	private Paint paint;
	private PointF startPoint = null;
	private PointF endPoint = null;
	private boolean isDrawing;
	// Pinch Code
	private static final int INVALID_POINTER_ID = -1;
	// The active pointer is the one currently moving our object.
	private int mActivePointerId = INVALID_POINTER_ID;
	private float mPosX;
	private float mPosY;
	private float mLastTouchX;
	private float mLastTouchY;
	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;

	public DrawImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Pinch Code
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

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
	
	public float getScale() {
		return mScaleFactor;
	}
	
	public Point getViewCenter() {
		int x = Math.round(mPosX);
		int y = Math.round(mPosY);
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

		if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "ZOOM") {
			canvas.save();
			canvas.translate(mPosX, mPosY);
			canvas.scale(mScaleFactor, mScaleFactor);
			// mIcon.draw(canvas);
			canvas.restore();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Pinch Code
		mScaleDetector.onTouchEvent(event);

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DRAG") {
				startPoint = new PointF(event.getX(), event.getY());
				endPoint = new PointF();
				isDrawing = true;
			}
			
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DEFAULT_TAP") {
				
			}
			
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "ZOOM") {
				// Pinch Code
				final float x = event.getX();
				final float y = event.getY();
				mLastTouchX = x;
				mLastTouchY = y;
				mActivePointerId = event.getPointerId(0);
			}

			break;

		case MotionEvent.ACTION_MOVE:
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DRAG") {
				if (isDrawing) {
					endPoint.x = event.getX();
					endPoint.y = event.getY();
				}
			}
			
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "DEFAULT_TAP") {
				
			}
			
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "ZOOM") {
				// Pinch Code
				final int pointerIndex = event
						.findPointerIndex(mActivePointerId);
				final float x2 = event.getX(pointerIndex);
				final float y2 = event.getY(pointerIndex);

				// Only move if the ScaleGestureDetector isn't processing a
				// gesture.
				if (!mScaleDetector.isInProgress()) {
					final float dx = x2 - mLastTouchX;
					final float dy = y2 - mLastTouchY;

					mPosX += dx;
					mPosY += dy;
				}

				mLastTouchX = x2;
				mLastTouchY = y2;
				
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
			
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "ZOOM") {
				mActivePointerId = INVALID_POINTER_ID;
			}
			break;
		case MotionEvent.ACTION_CANCEL: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}
		case MotionEvent.ACTION_POINTER_UP: {
			if (ActiveUserModel.getShared().getUser().getBoundingBoxSetting() == "ZOOM") {
				final int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
				final int pointerId = event.getPointerId(pointerIndex);
				if (pointerId == mActivePointerId) {
					// This was our active pointer going up. Choose a new
					// active pointer and adjust accordingly.
					final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
					mLastTouchX = event.getX(newPointerIndex);
					mLastTouchY = event.getY(newPointerIndex);
					mActivePointerId = event.getPointerId(newPointerIndex);
				}
			}
			
			break;
		}
		default:
			break;
		}
		return true;
	}

	@Override
	public void setImageBitmap(Bitmap picture) {
		super.setImageBitmap(picture);
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

			invalidate();
			return true;
		}
	}
}
