package com.example.helloworld;


import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
 
public class ImageAdapter extends BaseAdapter {
	private Context context;
	public final List<String> mobileValues;
 
	public ImageAdapter(Context context, List<String> mobileValues) {
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
			textView.setText(mobileValues.get(position));
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
 
			Integer mobile = Integer.getInteger(mobileValues.get(position));
 
			if (mobile != null && mobile % 4 == 0 ) {
				imageView.setImageResource(R.drawable.abc_ab_bottom_solid_dark_holo);
			} else if (mobile != null && mobile % 4 == 1) {
				imageView.setImageResource(R.drawable.abc_ab_bottom_solid_light_holo);
			} else if (mobile != null && mobile % 4 == 2) {
				imageView.setImageResource(R.drawable.abc_ab_solid_light_holo);
			} else {
				imageView.setImageResource(R.drawable.abc_ic_ab_back_holo_dark);
			}
 
		} else {
			gridView = (View) convertView;
		}
		((GridPositionGeter)this.context).SetCurrentPosition(position);
		return gridView;
	}
 
	@Override
	public int getCount() {
		return mobileValues.size();
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