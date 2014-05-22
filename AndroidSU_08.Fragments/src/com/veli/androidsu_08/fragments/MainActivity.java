package com.veli.androidsu_08.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;	
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {

	private DrawerLayout drawerLayoutNavDrawer;
	private ArrayAdapter<String> drawerAdapter;
	private ListView listViewDrawerOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listViewDrawerOptions = (ListView) findViewById(R.id.listViewDrawerOptions);

		drawerAdapter = new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, R.id.textViewListItemText,
				new String[] { "Option 1", "Option 2", "Option 3" });

		listViewDrawerOptions.setAdapter(drawerAdapter);

		drawerLayoutNavDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		listViewDrawerOptions.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View pressedView,
			int position, long id) {

		String itemText = ((TextView) pressedView
				.findViewById(R.id.textViewListItemText)).getText().toString();

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction();

		Fragment fragment = new ExchangeFragment();
		Bundle fragArgs = new Bundle();
		fragArgs.putString(ExchangeFragment.EXTRA_FRAGMENT_TEXTVIEW_TEXT,
				itemText);
		fragment.setArguments(fragArgs);

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		drawerLayoutNavDrawer.closeDrawers();
	}
}
