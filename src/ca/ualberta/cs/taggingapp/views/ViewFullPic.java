package ca.ualberta.cs.taggingapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import ca.ualberta.cs.taggingapp.R;
import ca.ualberta.cs.taggingapp.models.Picture;
import ca.ualberta.cs.taggingapp.models.PictureList;
import ca.ualberta.cs.taggingapp.models.TagList;
import ca.ualberta.cs.taggingapp.models.TaggedImageView;

public class ViewFullPic extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_full_pic);
		setTitle("Tagging App");
		
		Picture thePicture = PictureList.getInstance().getSelected();
		
		TaggedImageView img= (TaggedImageView) findViewById(R.id.picViewer);
		img.setImageBitmap(thePicture.getPicture());
		
		ListView miniTagsList = (ListView) this.findViewById(R.id.miniTagsList);

        final TagsListAdapter adapter = new TagsListAdapter(getApplicationContext(), R.layout.list_item, TagList.getInstance().getTags());
        
        miniTagsList.setAdapter(adapter); 
        
        miniTagsList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent,
					View v, int position, long id)
			{
				Intent i = new Intent(ViewFullPic.this, TagAndPhoto.class);
//				i.putExtra("tagName", adapter.getItem(position));
				i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(i);
				//ViewFullPic.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_full_pic_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handles presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.add:
	        	Intent i = new Intent(ViewFullPic.this, AddTag.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(i);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
