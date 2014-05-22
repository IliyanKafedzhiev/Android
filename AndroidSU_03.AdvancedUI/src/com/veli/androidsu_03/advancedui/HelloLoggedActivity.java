package com.veli.androidsu_03.advancedui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloLoggedActivity extends PresetMenuActivity {

	private TextView editTextHelloToUser;
	private Button buttonLogOut;
	private Activity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logged_user);

		this.context = this;
		
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor prefEditor = prefs.edit();
		prefEditor.putBoolean(MainActivity.USER_IS_LOGGED, true);
		prefEditor.apply();

		String username = prefs.getString(MainActivity.EXTRA_USERNAME,
				"undefined");

		this.editTextHelloToUser = (TextView) this
				.findViewById(R.id.editTextHelloToUser);
		this.buttonLogOut = (Button) this.findViewById(R.id.buttonLogOut);

		this.editTextHelloToUser.setText(String.format("%s%s",
				this.editTextHelloToUser.getText().toString(), username));

		this.buttonLogOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				unlogUser();
				startDefaultNotLoggedActivity();
			}

			private void startDefaultNotLoggedActivity() {
				Intent intentNotLoggedActivity = new Intent(context,
						MainActivity.class);
				startActivity(intentNotLoggedActivity);
			}

			private void unlogUser() {
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				SharedPreferences.Editor prefEditor = prefs.edit();
				prefEditor.remove(MainActivity.USER_IS_LOGGED);
				prefEditor.remove(MainActivity.EXTRA_USERNAME);
				prefEditor.commit();
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
