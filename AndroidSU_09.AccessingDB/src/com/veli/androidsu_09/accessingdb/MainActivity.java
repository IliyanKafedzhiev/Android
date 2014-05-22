package com.veli.androidsu_09.accessingdb;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static final String TAG = "TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DbAdapter.initialize(this);

		DbAdapter.openDatabase();

		logFirstByName("Toby");
		
		DbAdapter.addPerson(new Person("Toby"));

		logPeopleCount();

		logAllPeople();
		
		DbAdapter.closeDatabase();
	}

	private void logAllPeople() {
		List<Person> personList = DbAdapter.getAllPeople();

		for (Person person : personList) {
			Log.d(TAG, "id: " + person.getId() + "; name: " + person.getName());
		}
	}

	private void logPeopleCount() {
		Log.d(TAG, "People count: " + DbAdapter.getPeopleCount());
	}

	private void logFirstByName(String name) {
		Person firstByName = DbAdapter.getPersonByName(name);
		if (firstByName != null) {
			Log.d(TAG, "The first person with name " + name + 
					" has the following id: "
					+ String.valueOf(firstByName.getId()));
		} else {
			Log.d(TAG, "There are currently no people in the DB");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
