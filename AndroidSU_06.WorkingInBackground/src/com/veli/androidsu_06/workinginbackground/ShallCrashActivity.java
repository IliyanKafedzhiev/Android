package com.veli.androidsu_06.workinginbackground;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ShallCrashActivity extends PresetMenuActivity {

	private TextView textViewMy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shall_crash);
		
		this.textViewMy = (TextView) this.findViewById(R.id.textViewMyTextView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// modifying views (which belong to the main thread) will cause exceptions
				// on older API levels and may have unexpected behavior on newer API levels
				textViewMy.setText("Modifying TextView text outside of the main thread.");
				textViewMy.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
			}
		}).start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
