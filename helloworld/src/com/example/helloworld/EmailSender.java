package com.example.helloworld;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


@SuppressWarnings("unused")
public class EmailSender extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_sender);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener{

		private static Spinner AnAboutSpinner;
		private static EditText ClientEmail;
		private static Button SentButton;
		
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
	}

}
