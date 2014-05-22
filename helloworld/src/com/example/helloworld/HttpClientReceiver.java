package com.example.helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpClientReceiver {
		public static void connect(String url,String result)
	{

	    HttpClient httpclient = new DefaultHttpClient();

	    // Prepare a request object
	    HttpGet httpget = new HttpGet(url); 

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
	    		//Log.v("swe", "The response entity is: "
	    				//+ responseBody);
	    		result = responseBody;
	    	}


	    } catch (Exception e) {
	    	Log.d("swe","error", e);
	    }
	}

	    private static String convertStreamToString(InputStream is) {
	    /*
	     * To convert the InputStream to String we use the BufferedReader.readLine()
	     * method. We iterate until the BufferedReader return null which means
	     * there's no more data to read. Each line will appended to a StringBuilder
	     * and returned as String.
	     */
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
