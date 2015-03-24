package com.example.itemtest;

import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartupIntentReceiver extends BroadcastReceiver {

	private String action = "android.intent.action.MAIN";
	private String category = "android.intent.category.LAUNCHER";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		final Context t = context;
		try {
			Util.writeToFile("test.txt", "----------------");
			Util.writeToFile("test.txt", "Device is booted.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Intent s = new Intent(context, MainActivity.class);
		s.setAction(action);
		s.addCategory(category);
		s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(s);

	}

}
