package com.example.helloworld;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;



public class EmailSender extends ActionBarActivity {

    public boolean isConfimed;
    private PlaceholderFragment fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_sender);
		fragment = new PlaceholderFragment();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, fragment).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.basic_menu,menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public void onBackPressed() 
	{
		isConfimed = false;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.logout_message)
               .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
           				EmailSender.super.onBackPressed();
                   }
               })
               .setNegativeButton(R.string.stayHere, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               }).create().show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == R.id.item1)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setMessage(R.string.logout_message)
	               .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	    Intent run = new Intent(EmailSender.this,LoginActivity.class);
	           			    startActivity(run);
	                   }
	               })
	               .setNegativeButton(R.string.stayHere, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                   }
	               }).create().show();
		}
		else if (item.getItemId() == R.id.showPick) {
			
			
				fragment.Img.setVisibility(View.VISIBLE);
			
		}
		return super.onOptionsItemSelected(item);
	}

	
	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	public static class PlaceholderFragment extends Fragment implements OnClickListener,OnItemSelectedListener{

		private static Spinner AnAboutSpinner;
		private static EditText ClientEmail;
		private static Button SentButton;
		private ImageView Img;
		
		public PlaceholderFragment() {
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_email_sender,
					container, false);
			
			AnAboutSpinner = (Spinner) rootView.findViewById(R.id.spinnerAbout);
			ClientEmail =(EditText) rootView.findViewById(R.id.emailSenderEditText);
			SentButton = (Button) rootView.findViewById(R.id.buttonSent);
			SentButton.setOnClickListener(this);
			Img = (ImageView) rootView.findViewById(R.id.imageView1);
 			
			if(Img == null)
			{
				Log.d("problem","Img is null");
			}
			if(ClientEmail == null)
			{
				Log.d("problem","EditText is null");
			}
			if(AnAboutSpinner == null)
			{
				Log.d("problem","spineer is null");
			}
			
			return rootView;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}