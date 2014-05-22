package com.example.helloworld;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
 
public class GridViewActivity extends Activity implements GridPositionGeter {
 
	private GridView gridView;
	private int CurPos;
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			CurPos = gridView.getFirstVisiblePosition();
			int newPosition = GetCurrentPosition() - 3;
			Log.d("problem",String.valueOf(GetCurrentPosition()) + " -current Position");
			Log.d("problem",String.valueOf(gridView.getAdapter().getCount()) + " adapter.Count()");
			gridView.setSelection((newPosition >0)?newPosition:0);
		}
		super.onConfigurationChanged(newConfig);
	}


 
	public List<String> MOBILE_OS;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		MOBILE_OS= new LinkedList<String>();
		MOBILE_OS.addAll( Arrays.asList( 
				"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
				"20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39",
				"40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59",
				"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
				"20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39",
				"40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"));
		
		gridView = (GridView) findViewById(R.id.gridView1);
 
		gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));
 
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id)
			{
					Toast.makeText(
				    getApplicationContext(),
				    ((TextView) v.findViewById(R.id.grid_item_label))
				    .getText(), Toast.LENGTH_SHORT).show();
					MOBILE_OS.remove(position);
					Log.d("problem", String.valueOf(position) + "-position");
					((BaseAdapter)gridView.getAdapter()).notifyDataSetChanged();
			}
		});
 
	}

	@Override
	public int GetCurrentPosition() {
		return CurPos;
	}

	@Override
	public void SetCurrentPosition(int arg) {
		this.CurPos = arg;
		
	}
 
}