package com.example.helloworld;

import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class ThreadForDownload extends AsyncTask<Void, Void, UserStackOverFlow>{

	HttpTest activity;
	@Override
	protected UserStackOverFlow doInBackground(Void... params) {
		UserStackOverFlow response1=null;
		Log.d("swe","beforess");
		
		HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet("http://api.stackoverflow.com/1.1/users/546661/answers"); 

	    // Execute the request
	    HttpResponse response;
	    try {
	    	response = httpclient.execute(httpget);
	    	String responseBody = null;
	    	if (response != null && response.getEntity() != null) {
	    		if (response.getEntity() != null) {
	    			GZIPInputStream gzipInputStream = new GZIPInputStream(
	    					response.getEntity().getContent());
	    			byte[] buffer = new byte[1024];
	    			int read;
	    			StringBuffer stringBuffer = new StringBuffer();
	    			while ((read = gzipInputStream.read(buffer)) >= 0) {
	    				stringBuffer.append(new String(buffer, 0, read));
	    			}
	    			responseBody = stringBuffer.toString();
	    		}
	    		Gson gson = new Gson();
	    		response1 = gson.fromJson(responseBody , UserStackOverFlow.class);
	    	}


	    } catch (Exception e) {
	    	Log.d("swe","error", e);
	    }
		
		return response1;
	}
	@Override
	protected void onPostExecute(UserStackOverFlow result) {
		Log.d("swe",result.toString());
		((TextView)activity.findViewById(R.id.http_content)).setText(result.toString());
		super.onPostExecute(result);
	}

}
