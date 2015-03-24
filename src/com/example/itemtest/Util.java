package com.example.itemtest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {
	public final static String PHONE_BASE_PATH = "sdcard/rickyTest/";
	public final static String ACTION_RECORD = "com.example.itemtest.craving.ACTION_RECORD";
	public static String curBatt;

	public static String getConnectionState(Context ctx) {
		ConnectivityManager manager = (ConnectivityManager) ctx.getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return "Hardware Problem";
		}
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return "NetworkInfo Error";
		}
		if (networkInfo.isAvailable()) {
			return "Connected";
		}
		return "Not Connected";

	}

	public static void writeToFile(String fileName, String toWrite) throws IOException {
		File dir = new File(PHONE_BASE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File f = new File(PHONE_BASE_PATH, fileName);
		FileWriter fw = new FileWriter(f, true);
		fw.write(toWrite + '\n');
		fw.flush();
		fw.close();
		f = null;
	}

	public static void scheduleRecording(Context context) {
		Intent i = new Intent(Util.ACTION_RECORD);
		context.sendBroadcast(i);
	}

}
