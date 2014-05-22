package com.example.helloworld;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String,Integer,Boolean>{

	ProgressDialog dialog;
	LoginActivity activity;
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		dialog.hide();
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		dialog = ProgressDialog.show(activity, "Progress", "0/10");
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		dialog.setMessage(values[0].toString()+"/10");
		super.onProgressUpdate(values);
	}

	@Override
	protected Boolean doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			publishProgress(i);
		}
		
		return null;
	}

}
