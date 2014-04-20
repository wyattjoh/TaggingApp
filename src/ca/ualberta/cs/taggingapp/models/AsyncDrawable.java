package ca.ualberta.cs.taggingapp.models;

import java.lang.ref.WeakReference;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import ca.ualberta.cs.taggingapp.controllers.BitmapWorkerTask;

public class AsyncDrawable extends BitmapDrawable {
	private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

	public AsyncDrawable(Resources res, Bitmap bitmap,
			BitmapWorkerTask bitmapWorkerTask) {
		super(res, bitmap);
		bitmapWorkerTaskReference = new WeakReference<BitmapWorkerTask>(
				bitmapWorkerTask);
	}

	public BitmapWorkerTask getBitmapWorkerTask() {
		return bitmapWorkerTaskReference.get();
	}
}