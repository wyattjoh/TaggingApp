package ca.ualberta.cs.taggingapp.controllers;

import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;

import ca.ualberta.cs.taggingapp.models.AsyncDrawable;
import ca.ualberta.cs.taggingapp.models.ImageLoadingFactory;
import ca.ualberta.cs.taggingapp.models.Picture;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Loadings the bitmap from SD card on the Android phone. 
 * 
 **/

public class BitmapWorkerTask extends AsyncTask<Uri, Void, Bitmap> {
	private final WeakReference<ImageView> imageViewReference;
	private final int imageSize;
	private Uri data = null;
	private Picture thePicture = null;

	public BitmapWorkerTask(Picture picture, ImageView imageView,
			int imageViewSize) {
		// Use a WeakReference to ensure the ImageView can be garbage collected
		imageViewReference = new WeakReference<ImageView>(imageView);
		imageSize = imageViewSize;
		thePicture = picture;
	}

	/**
	 * Decode the images in the background
	 */
	@Override
	protected Bitmap doInBackground(Uri... params) {
		data = params[0];

		try {
			return ImageLoadingFactory.decodeScaledBitmapFromUri(data,
					imageSize);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 *  Once complete, see if ImageView is still around and set bitmap.
	 */
	@Override
	protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            final BitmapWorkerTask bitmapWorkerTask =
                    getBitmapWorkerTask(imageView);
            if (this == bitmapWorkerTask && imageView != null) {
                imageView.setImageBitmap(bitmap);
                
				if (thePicture != null) {
					thePicture.setPicture(bitmap);
				}
            }
        }
	}

	/**
	 * Cancels the potential work
	 * @param data
	 * @param imageView
	 * @return
	 */
	public static boolean cancelPotentialWork(Uri data, ImageView imageView) {
		final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

		if (bitmapWorkerTask != null) {
			final Uri bitmapData = bitmapWorkerTask.data;
			// If bitmapData is not yet set or it differs from the new data
			if (bitmapData == null || bitmapData != data) {
				// Cancel previous task
				bitmapWorkerTask.cancel(true);
			} else {
				// The same work is already in progress
				return false;
			}
		}
		// No task associated with the ImageView, or an existing task was
		// cancelled
		return true;
	}

	/**
	 * Fetches the worker task for the specific image view
	 * 
	 * @param imageView
	 * @return
	 */
	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		if (imageView != null) {
			final Drawable drawable = imageView.getDrawable();
			if (drawable instanceof AsyncDrawable) {
				final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
				return asyncDrawable.getBitmapWorkerTask();
			}
		}
		return null;
	}
}
