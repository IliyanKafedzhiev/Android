package com.example.helloworld;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
 
public class ImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
 
	public ImageAdapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.cell_in_grid_view, null);
 
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(mobileValues[position]);
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
 
			String mobile = mobileValues[position];
 
			if (mobile.equals("Windows")) {
				imageView.setImageResource(R.drawable.abc_ab_bottom_solid_dark_holo);
			} else if (mobile.equals("iOS")) {
				imageView.setImageResource(R.drawable.abc_ab_bottom_solid_light_holo);
			} else if (mobile.equals("Blackberry")) {
				imageView.setImageResource(R.drawable.abc_ab_solid_light_holo);
			} else {
				imageView.setImageResource(R.drawable.abc_ic_ab_back_holo_dark);
			}
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return mobileValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}