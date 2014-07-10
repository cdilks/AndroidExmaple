package com.AlbumArt.POJO;

public class Album {

	private String date;
	private String country;
	private String track_url;
	private String track_name;
	private String artist_name;
	private String artist_url;
	private String album_name;
	private String album_url;
	private String artwork_url;
	private String num_streams;

	public Album(){

	}

	public Album(String date, String country, String track_url,
			String track_name, String artist_name, String artist_url,
			String album_name, String album_url, String artwork_url,
			String num_streams) {
		super();
		this.date = date;
		this.country = country;
		this.track_url = track_url;
		this.track_name = track_name;
		this.artist_name = artist_name;
		this.artist_url = artist_url;
		this.album_name = album_name;
		this.album_url = album_url;
		this.artwork_url = artwork_url;
		this.num_streams = num_streams;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTrack_url() {
		return track_url;
	}

	public void setTrack_url(String track_url) {
		this.track_url = track_url;
	}

	public String getTrack_name() {
		return track_name;
	}

	public void setTrack_name(String track_name) {
		this.track_name = track_name;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getArtist_url() {
		return artist_url;
	}

	public void setArtist_url(String artist_url) {
		this.artist_url = artist_url;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getAlbum_url() {
		return album_url;
	}

	public void setAlbum_url(String album_url) {
		this.album_url = album_url;
	}

	public String getArtwork_url() {
		return artwork_url;
	}

	public void setArtwork_url(String artwork_url) {
		this.artwork_url = artwork_url;
	}

	public String getNum_streams() {
		return num_streams;
	}

	public void setNum_streams(String num_streams) {
		this.num_streams = num_streams;
	}

	@Override
	public String toString() {
		return "Album [date=" + date + ", country=" + country + ", track_url="
				+ track_url + ", track_name=" + track_name + ", artist_name="
				+ artist_name + ", artist_url=" + artist_url + ", album_name="
				+ album_name + ", album_url=" + album_url + ", artwork_url="
				+ artwork_url + ", num_streams=" + num_streams + "]";
	}

}
