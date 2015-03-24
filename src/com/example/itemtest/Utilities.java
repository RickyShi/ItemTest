package com.example.itemtest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utilities {
	public final static String PHONE_BASE_PATH = "sdcard/rickyTest/";
	public final static String ACTION_RECORD = "com.example.itemtest.ACTION_RECORD";
	public final static String LINEBREAK = System.getProperty("line.separator");
	public final static String SPLIT = "---------------------------------------";
	public static final String RECORDING_CATEGORY = "Recording";

	// Later Need to be changed after merging
	public final static String SINGLE_UPLOAD_ADDRESS = "http://dslsrv8.cs.missouri.edu/~hw85f/Server/Crt2/dealWithBackupRAW.php";

	// Duplicated Thing after merging
	public final static int PREFIX_LEN = 35;

	public static String curBatt = "nan";

	public static String getConnectionState(Context ctx) {
		ConnectivityManager manager = (ConnectivityManager) ctx.getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return "Hardware Problem";
		}
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return "Not Connected";
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
		Intent i = new Intent(Utilities.ACTION_RECORD);
		context.sendBroadcast(i);
	}

	public static String getCurrentTimeStamp()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("US/Central"));
		return String.valueOf(cal.getTime());
	}

	public static String getFileDate()
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat curFormater = new SimpleDateFormat("MMMMM_dd");
		String dateObj = curFormater.format(c.getTime());
		return dateObj;
	}
}
