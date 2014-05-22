package com.example.androidsu_07.accessingtheweb;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

public class DownloadUserDataAndFillListViewAsyncTask extends
		AsyncTask<Integer, Void, UserAnswerListResponse> {

	private static final String TAG = "TAG";
	private String url;
	private Activity activity;
	private UserAnswerListResponse resultData;

	public DownloadUserDataAndFillListViewAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected UserAnswerListResponse doInBackground(Integer... params) {

		int userId = params[0];
		this.url = "https://api.stackexchange.com/2.2/users/" + userId
				+ "/answers?site=stackoverflow&filter=!9WA((OwZp";
		Log.d(TAG, this.url);
		String jsonStringResult = executeGetCall();
		Log.d(TAG, jsonStringResult);

		Gson gson = new Gson();
		resultData = gson.fromJson(jsonStringResult,
				UserAnswerListResponse.class);

		return resultData;
	}

	@Override
	protected void onPostExecute(UserAnswerListResponse result) {
		super.onPostExecute(result);

		ProgressBar pb = (ProgressBar) this.activity
				.findViewById(R.id.progresBarLoading);
		((RelativeLayout) pb.getParent()).removeView(pb);

		UserAnswersAdapter adapter = new UserAnswersAdapter(this.activity,
				R.id.listViewUserAnswersListItem, this.resultData);
		((ListView) activity.findViewById(R.id.listViewUserAnswers))
				.setAdapter(adapter);
	}

	private String executeGetCall() {

		String jsonResult = null;
		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(url);

		// Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			// Examine the response status

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {

				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				jsonResult = decodeGZIPResponse(instream);
				// now you have the string representation of the HTML request
				instream.close();
			}

		} catch (Exception e) {
		}

		return jsonResult;
	}

	private static String decodeGZIPResponse(InputStream content)
			throws IllegalStateException, IOException {
		String stringContent = null;

		GZIPInputStream gzipInputStream = new GZIPInputStream(content);
		byte[] buffer = new byte[1024];
		int read;
		StringBuffer stringBuffer = new StringBuffer();
		while ((read = gzipInputStream.read(buffer)) >= 0) {
			stringBuffer.append(new String(buffer, 0, read));
		}
		stringContent = stringBuffer.toString();

		return stringContent;
	}
}
