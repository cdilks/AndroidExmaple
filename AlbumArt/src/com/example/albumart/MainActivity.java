package com.example.albumart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ViewSwitcher;


public class MainActivity extends ActionBarActivity {

	Spotify spotify;
	Context ctx;
	GridView gridView;
	ViewSwitcher switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ctx = this;
        gridView = (GridView) findViewById(R.id.album_grid);
        switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        gridView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ctx, AlbumMetaData.class);
				intent.putExtra("album", spotify.getAlbums().get(position).getAlbum_name());
				intent.putExtra("artist", spotify.getAlbums().get(position).getArtist_name());
				intent.putExtra("image", spotify.getAlbums().get(position).getArtwork_url());

				startActivity(intent);
				overridePendingTransition(R.anim.left_animatiion,
		                   R.anim.left_leave_animation);
			    }
        });
        gridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to your AdapterView
            	if(totalItemsCount<50)
                spotify.loadMoreImages(totalItemsCount);

            }
            });
        spotify = new Spotify(ctx,gridView,switcher);
        spotify.getMostStreamed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
