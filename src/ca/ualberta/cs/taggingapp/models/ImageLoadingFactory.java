package ca.ualberta.cs.taggingapp.models;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import ca.ualberta.cs.taggingapp.controllers.BitmapWorkerTask;

/**
 * A factory class that receives a uri of an image and loads the image.
 * 
 * */

public class ImageLoadingFactory {
	private static ImageLoadingFactory singleton = null;
	private Context context;

	private ImageLoadingFactory(Context theContext) {
		this.context = theContext;
	}

	public static ImageLoadingFactory createInstance(Context theContext) {
		if (singleton == null) {
			singleton = new ImageLoadingFactory(theContext);
		}
		return singleton;
	}

	private static ImageLoadingFactory getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Can't get an instance of ImageLoadingFactory without creating one with a context!");
		}
		return singleton;
	}

	// gets the bitmap from the provided uri
	public static Bitmap getImageFromUri(Uri imageUri)
			throws FileNotFoundException, IOException {
		return MediaStore.Images.Media.getBitmap(
				getInstance().context.getContentResolver(), imageUri);
	}

	// decodes the scaled uri passed in and returns a bitmap
	public static Bitmap decodeScaledBitmapFromUri(Uri uri, int requiredSize)
			throws FileNotFoundException {
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(getInstance().context.getContentResolver()
				.openInputStream(uri), null, o);

		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;

		while (true) {
			if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
				break;
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}

		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		return BitmapFactory.decodeStream(getInstance().context
				.getContentResolver().openInputStream(uri), null, o2);
	}

	public static void loadBitmap(Picture picture, Uri resId,
			ImageView imageView, int imageSize, boolean shouldCache) {
		if (BitmapWorkerTask.cancelPotentialWork(resId, imageView)) {
			final BitmapWorkerTask task;
			if (shouldCache) {
				task = new BitmapWorkerTask(picture, imageView, imageSize);
			} else {
				task = new BitmapWorkerTask(null, imageView, imageSize);
			}

			final AsyncDrawable asyncDrawable = new AsyncDrawable(
					getInstance().context.getResources(), null, task);
			imageView.setImageDrawable(asyncDrawable);
			task.execute(resId);
		}
	}
}
