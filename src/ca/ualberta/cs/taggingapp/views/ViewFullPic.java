package ca.ualberta.cs.taggingapp.views;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;

public class ViewFullPic extends Activity {
	
	private ArrayList <String> tags = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_full_pic);
		setTitle("Tagging App");
		
		Bundle extras = getIntent().getExtras();
		
		// TODO: WYATT: FX
		Picture thePicture = PictureList.getInstance().getSelected();
		
		ImageView img= (ImageView) findViewById(R.id.picViewer);
		img.setImageBitmap(thePicture.getPicture());
		
		Random randomGenerator = new Random();
        for (int i = 0; i < 10; i++){
          int randomInt = randomGenerator.nextInt(10000);
          tags.add("#" + Integer.toString(randomInt));
        }
		
		ListView miniTagsList = (ListView) this.findViewById(R.id.miniTagsList);
        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, tags);
        
        miniTagsList.setAdapter(adapter); 
        
        miniTagsList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent,
					View v, int position, long id)
			{
				Intent i = new Intent(ViewFullPic.this, TagAndPhoto.class);
				i.putExtra("tagName", adapter.getItem(position));
				i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(i);
				//ViewFullPic.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_full_pic, menu);
		return true;
	}

}
