package com.veli.androidsu_03.advancedui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.FormatException;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends PresetMenuActivity {

	public final static String EXTRA_USERNAME = "USERNAME";
	public final static String USER_IS_LOGGED = "USER_IS_LOGGED";

	private Button buttonLogin;
	private EditText editTextUsername;
	private EditText editTextPassword;

	private final static String EXT_FILE_NAME = "authDetails.txt";
	private final static String PRESET_USERNAME = "Gosho";
	private final static String PRESET_PASSWORD = "strongpass";

	private String username = null;
	private String password = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setTitle("Login Page");

		Boolean userIsLogged = checkUserIsLogged();

		if (userIsLogged) {
			startLoggedInActivity();
		} else {

			if (externalStorageAvailableAndWritable()) {
				readUsernameAndPasswordFromExternalFile();
			} else {
				this.username = PRESET_USERNAME;
				this.password = PRESET_PASSWORD;
			}

			initializeViews();
			attachViewEvents();
		}
	}

	private Boolean externalStorageAvailableAndWritable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	private void startLoggedInActivity() {
		Intent redirectToLoggedActivity = new Intent(getApplicationContext(),
				HelloLoggedActivity.class);
		redirectToLoggedActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(redirectToLoggedActivity);
	}

	private Boolean checkUserIsLogged() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		Boolean userIsLogged = prefs.getBoolean(USER_IS_LOGGED, false);
		return userIsLogged;
	}

	private void attachViewEvents() {
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String inputUsername = editTextUsername.getText().toString();
				String inputPassword = editTextPassword.getText().toString();

				if (validateUserInput(inputUsername, inputPassword)) {
					saveUsernameToSharedPrefs(inputUsername);
					startLoggedInActivity();
				} else {
					Toast.makeText(getApplicationContext(),
							"Incorrect password or username",
							Toast.LENGTH_SHORT).show();
				}
			}

			private void saveUsernameToSharedPrefs(String inputUsername) {
				SharedPreferences prefs = PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext());
				SharedPreferences.Editor prefEditor = prefs.edit();
				prefEditor
						.putString(MainActivity.EXTRA_USERNAME, inputUsername);
				prefEditor.apply();
			}

			private boolean validateUserInput(String inputUsername,
					String inputPassword) {
				return inputUsername.equals(username)
						&& inputPassword.equals(password);
			}
		});
	}

	private void initializeViews() {
		this.buttonLogin = (Button) this.findViewById(R.id.buttonLogin);
		this.editTextUsername = (EditText) this
				.findViewById(R.id.editTextUsername);
		this.editTextPassword = (EditText) this
				.findViewById(R.id.editTextPassword);
	}

	private void readUsernameAndPasswordFromExternalFile() {

		String externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String fileName = EXT_FILE_NAME;
		File file = createAuthFileIfNotExists(externalStoragePath, fileName);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				processAuthFileLine(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FormatException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void processAuthFileLine(String line) throws FormatException {
		String[] resultArr = line.trim().split("\\s*[:]\\s*");
		if (resultArr.length != 2) {
			throw new FormatException("The line is not in the correct format: "
					+ line);
		}

		String key = resultArr[0].toLowerCase().trim();
		String value = resultArr[1].trim();

		if (key.equals("username")) {
			this.username = value;
		} else if (key.equals("password")) {
			this.password = value;
		} else {
			throw new FormatException("The line is not in the correct format: "
					+ line);
		}
	}

	private File createAuthFileIfNotExists(String filePath, String fileName) {
		File file = new File(filePath, fileName);
		if (!file.exists() || file.isDirectory()) {
			Writer writer = null;

			String newLine = System.getProperty("line.separator");
			try {

				FileOutputStream os = new FileOutputStream(file);

				writer = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
				writer.write(String.format("%s%s", "Username :  "
						+ PRESET_USERNAME, newLine));
				writer.write(String.format("%s%s", "Password:"
						+ PRESET_PASSWORD, newLine));

			} catch (IOException ex) {
				// report
			} finally {
				try {
					writer.close();
				} catch (Exception ex) {
				}
			}
		}

		return file;
	}
}
