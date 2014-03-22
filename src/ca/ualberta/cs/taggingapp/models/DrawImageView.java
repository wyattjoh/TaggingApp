package ca.ualberta.cs.taggingapp.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

public class DrawImageView extends ImageView {
	
	
	public DrawImageView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	public void setImageBitmap(Bitmap picture) {
		super.setImageBitmap(picture);
	}
}