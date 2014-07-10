package com.example.albumart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.ViewSwitcher;

import com.AlbumArt.POJO.Album;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Spotify {

	Context ctx;
	private List<Album> albums;
	private List<Drawable> covers;
	GridView gridView;
	ViewSwitcher switcher;
	ImageAdapter adapter;

	public Spotify(Context ctx,GridView gridView,ViewSwitcher switcher){
		this.ctx = ctx;
		this.albums = new ArrayList<Album>();
		this.covers = new ArrayList<Drawable>();
		this.gridView = gridView;
		this.switcher = switcher;
	}

	public List<Album> getAlbums() {
		return albums;
	}



	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}



	public List<Drawable> getCovers() {
		return covers;
	}



	public void setCovers(List<Drawable> covers) {
		this.covers = covers;
	}



	protected void getMostStreamed(){

		new getRequest().execute("http://charts.spotify.com/api/charts/most_streamed/us/latest");

	}

	protected void loadMoreImages(int start){
		Log.d("Starting Index", Integer.toString(start));
		new loadImages().execute(start);

	}

	private class getRequest extends AsyncTask<String, Integer, String> {
	     @Override
        protected String doInBackground(String... rawr) {

    			try {
    				InputStream input = new URL("http://charts.spotify.com/api/charts/most_streamed/us/latest").openStream();
    				BufferedReader streamReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
    			    StringBuilder responseStrBuilder = new StringBuilder();

    			    String inputStr;
    			    while ((inputStr = streamReader.readLine()) != null)
    			        responseStrBuilder.append(inputStr);
    			    JSONObject json = new JSONObject(responseStrBuilder.toString());

    		    JSONArray jsonArray = json.getJSONArray("tracks");
    		    Album album;
    			for(int i = 0; i < jsonArray.length(); i++) {
    				ObjectMapper mapper = new ObjectMapper();
    				album= mapper.readValue(jsonArray.get(i).toString(), Album.class);
    				albums.add(album);
    			}

    			InputStream stream;
    			Drawable d;
    			for(int i = 0; i <  18; i++) {
    				stream = (InputStream) new URL(albums.get(i).getArtwork_url()).getContent();
    				d = Drawable.createFromStream(stream, "src");
    				covers.add(d);
    			}

    		} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "";


	     }

	     @Override
        protected void onProgressUpdate(Integer... progress) {

	     }

	     @Override
        protected void onPostExecute(String empty) {
	    	 adapter = new ImageAdapter(ctx,covers);
	    	 gridView.setAdapter(adapter);
	    	 switcher.showNext();
	     }
	 }

	private class loadImages extends AsyncTask<Integer, Integer, String> {
	     @Override
        protected String doInBackground(Integer... start) {
	    	 InputStream stream;
				Drawable d;
				for(int i = start[0]; i < start[0]+6; i++) {
					if(albums.size() <= i) break;
					try {
						stream = (InputStream) new URL(albums.get(i).getArtwork_url()).getContent();
						d = Drawable.createFromStream(stream, "src");
						covers.add(d);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
	    	 return "";
	     }

	     @Override
        protected void onProgressUpdate(Integer... progress) {}

	     @Override
        protected void onPostExecute(String empty) {
	    	 adapter.notifyDataSetChanged();
             gridView.invalidateViews();
	     }
	 }

}
