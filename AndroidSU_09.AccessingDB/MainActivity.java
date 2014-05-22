package com.veli.androidsu_09.accessingdb;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DbAdapter.initialize(this);
		
		DbAdapter.openDatabase();
		
		DbAdapter.createPerson("Toby");
		
		List<Person> personList = DbAdapter.getAllPersons();
		
		DbAdapter.closeDatabase();
		
		for (Person person : personList) {
			Log.d("hoho", "id: " + person.getId() + "; name: " + person.getName());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
