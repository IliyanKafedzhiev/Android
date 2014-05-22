package com.veli.androidsu_03.advancedui;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The class defines a preset state and actions for options menu.
 */
public abstract class PresetMenuActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_login:
			startActivity(createActivityIntent(MainActivity.class));
			break;
		case R.id.action_calculator:
			startActivity(createActivityIntent(CalculatorActivity.class));
			break;
		case R.id.action_mailclient:
			startActivity(createActivityIntent(MailClientActivity.class));
			break;
		default:
			return super.onOptionsItemSelected(item);
		}

		return false;
	}

	private Intent createActivityIntent(Object theClass) {
		Intent startActivity = new Intent(getApplicationContext(), (Class<?>) theClass);
		// make sure only one instance of each activity will be present: 
		startActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		return startActivity;
	}
}
