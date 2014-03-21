package com.example.taggingapp.View;

import java.util.ArrayList;
import java.util.Random;

import com.example.taggingapp.R;
import com.example.taggingapp.R.id;
import com.example.taggingapp.R.layout;
import com.example.taggingapp.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewFullPic extends Activity {
	
	private ArrayList <String> tags = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_full_pic);
		setTitle("Tagging App");
		
		Bundle extras = getIntent().getExtras();
		final Integer picId = (Integer) extras.get("imageName");
		
		ImageView img= (ImageView) findViewById(R.id.picViewer);
		img.setImageResource(picId);
		
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
			public void onItemClick(AdapterView<?> parent,
					View v, int position, long id)
			{
				Intent i = new Intent(ViewFullPic.this, TagAndPhoto.class);
				i.putExtra("tagName", adapter.getItem(position));
				i.putExtra("imageName", (Integer)picId);
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
