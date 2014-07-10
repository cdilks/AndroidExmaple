package com.example.albumart;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AlbumMetaData extends ActionBarActivity {

	TextView artistName;
	TextView albumName;
	SquareImageView albumArt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);

		setContentView(R.layout.album_meta_data);

		Bundle extras = getIntent().getExtras();

		artistName = (TextView)findViewById(R.id.artist_name);
		artistName.setText(extras.getString("artist"));
		albumName = (TextView)findViewById(R.id.album_name);
		albumName.setText(extras.getString("album"));
		albumArt = (SquareImageView)findViewById(R.id.album_art);
		new loadAlbumArt().execute(extras.getString("image"));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.album_meta_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:
        	finish();
        	overridePendingTransition(R.anim.right_animation,
	                   R.anim.right_leave_animation);
            return true;
    }
    return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
	    finish();//go back to the previous Activity
	    overridePendingTransition(R.anim.right_animation,
                R.anim.right_leave_animation);
	}

	private class loadAlbumArt extends AsyncTask<String, Integer, Drawable> {
	     @Override
        protected Drawable doInBackground(String... url) {
			InputStream stream;
			Drawable d = null;

			try {
				stream = (InputStream) new URL(url[0]).getContent();
				d = Drawable.createFromStream(stream, "src");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return d;


	     }

	     @Override
        protected void onProgressUpdate(Integer... progress) {

	     }

	     @Override
        protected void onPostExecute(Drawable image) {
	    	 albumArt.setImageDrawable(image);
	     }
	 }


}
