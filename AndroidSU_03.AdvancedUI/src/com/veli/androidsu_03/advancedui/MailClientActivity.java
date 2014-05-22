package com.veli.androidsu_03.advancedui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MailClientActivity extends PresetMenuActivity {

	private EditText editTextEmailFrom;
	private Spinner spinnerEmailSubject;
	private EditText editTextEmailMessageBody;
	private Button buttonSendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mailclient);

		this.initializeViews();

		this.buttonSendEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String emailAddressTo = editTextEmailFrom.getText().toString();
				String emailSubject = spinnerEmailSubject.getSelectedItem()
						.toString();
				String emailMessageBody = editTextEmailMessageBody.getText()
						.toString();

				Intent intentSendMail = composeEmailActivity(emailAddressTo,
						emailSubject, emailMessageBody);

				try {
					startActivity(Intent.createChooser(intentSendMail,
							"Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {

					Toast.makeText(MailClientActivity.this,
							"There are no email clients installed.",
							Toast.LENGTH_SHORT).show();
				}
			}

			private Intent composeEmailActivity(String emailAddressTo,
					String emailSubject, String emailMessageBody) {
				Intent intentSendMail = new Intent(Intent.ACTION_SEND);
				intentSendMail.setType("message/rfc822");
				intentSendMail.putExtra(Intent.EXTRA_EMAIL,
						new String[] { emailAddressTo });
				intentSendMail.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
				intentSendMail.putExtra(Intent.EXTRA_TEXT, emailMessageBody);
				return intentSendMail;
			}
		});
	}

	private void initializeViews() {
		this.editTextEmailFrom = (EditText) findViewById(R.id.editTextTo);
		this.spinnerEmailSubject = (Spinner) findViewById(R.id.spinnerEmailSubject);
		this.editTextEmailMessageBody = (EditText) findViewById(R.id.editTextEmailtMessageBody);
		this.buttonSendEmail = (Button) findViewById(R.id.buttonSendEmail);
	}
}
