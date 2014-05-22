package com.veli.AndroidSU_02.BasicUI.basicui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ScrollView viewTopParent = (ScrollView) findViewById(R.id.layout_main_activity);
		ViewGroup viewContentFrame = (ViewGroup) viewTopParent.findViewById(R.id.contentFrame);
		
		addButton(viewContentFrame);
		
		addEventListeners();
	}

	private void addButton(ViewGroup view) {
		button1 = new Button(this);
		button1.setText("Dynamically Added Button");

		view.addView(button1);
	}

	private void addEventListeners() {
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast clickMsgToast = Toast.makeText(getApplicationContext(),
						"This is my toast!", Toast.LENGTH_SHORT);
				clickMsgToast.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
