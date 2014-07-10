package com.example.albumart;


import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class ImageAdapter extends BaseAdapter implements ListAdapter {

	private final Context context;

	private final List<Drawable> covers;



	public ImageAdapter(Context context,List<Drawable> covers) {
		super();
		this.context = context;
		this.covers= covers;
	}

	@Override
	public int getCount() {
		return covers.size();
	}

	@Override
	public Object getItem(int position) {

		return covers.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		SquareImageView imageView;
		if(convertView == null) {
			imageView = new SquareImageView(context);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5,5,5,5);
		} else {
			imageView = (SquareImageView) convertView;
		}
		imageView.setImageDrawable(covers.get(position));
		return imageView;
	}

}