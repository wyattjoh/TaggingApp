package ca.ualberta.cs.taggingapp.models;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

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

	public static Bitmap getImageFromUri(Uri imageUri)
			throws FileNotFoundException, IOException {
		return MediaStore.Images.Media.getBitmap(
				getInstance().context.getContentResolver(), imageUri);
	}
}
