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

	/**
	 * Gets the bitmap from the provided uri
	 * @param imageUri
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Bitmap getImageFromUri(Uri imageUri)
			throws FileNotFoundException, IOException {
		return MediaStore.Images.Media.getBitmap(
				getInstance().context.getContentResolver(), imageUri);
	}

	/**
	 * decodes the scaled uri passed in and returns a bitmap
	 * @param uri
	 * @param requiredSize
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap decodeScaledBitmapFromUri(Uri uri, int requiredSize)
			throws FileNotFoundException {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(getInstance().context.getContentResolver()
				.openInputStream(uri), null, options);

		options.inSampleSize = calculateInSampleSize(options, requiredSize, requiredSize);
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeStream(getInstance().context
				.getContentResolver().openInputStream(uri), null, options);
	}

	/**
	 * Calculates the image sample size for the image loading process
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	/**
	 * Sends request to load the image from the SD card
	 * @param picture
	 * @param resId
	 * @param imageView
	 * @param imageSize
	 * @param shouldCache
	 */
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
