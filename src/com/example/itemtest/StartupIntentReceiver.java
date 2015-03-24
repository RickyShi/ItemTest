package com.example.itemtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class StartupIntentReceiver extends BroadcastReceiver {

	private String action = "android.intent.action.MAIN";
	private String category = "android.intent.category.LAUNCHER";

	@Override
	public void onReceive(Context context, Intent intent) {
		String fileName = Utilities.RECORDING_CATEGORY + "." + "9998" + "." + Utilities.getFileDate();
		String toWrite = Utilities.getCurrentTimeStamp() + Utilities.LINEBREAK + "Device is booted."
				+ Utilities.LINEBREAK + Utilities.SPLIT;
		try {
			Utilities.writeToFile(fileName + ".txt", toWrite);
		} catch (IOException e) {
			e.printStackTrace();
		}

		TransmitData transmitData = new TransmitData();
		transmitData.execute(fileName, toWrite);

		Intent s = new Intent(context, MainActivity.class);
		s.setAction(action);
		s.addCategory(category);
		s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(s);

	}

	private class TransmitData extends AsyncTask<String, Void, Boolean> {
		@Override
		protected Boolean doInBackground(String... strings) {
			// String data = strings[0];
			String fileName = strings[0];
			String dataToSend = strings[1];

			HttpPost request = new HttpPost(Utilities.SINGLE_UPLOAD_ADDRESS);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// params.add(new BasicNameValuePair("data", data));
			// file_name
			params.add(new BasicNameValuePair("file_name", fileName));
			// data
			params.add(new BasicNameValuePair("data", dataToSend));
			try {
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				HttpResponse response = new DefaultHttpClient().execute(request);
				Log.d("Sensor Data Point Info", String.valueOf(response.getStatusLine().getStatusCode()));
				return true;
			} catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
	}
}
