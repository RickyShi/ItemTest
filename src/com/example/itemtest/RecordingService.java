package com.example.itemtest;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

public class RecordingService extends Service {

	private String connection;
	BatteryInfoBroadcastReceiver batteryBroadcast;

	@Override
	public void onCreate() {
		super.onCreate();
		batteryBroadcast = new BatteryInfoBroadcastReceiver();
		this.registerReceiver(batteryBroadcast, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		// need to use alarm manager later
		connection = Util.getConnectionState(getApplicationContext());
		try {
			Util.writeToFile("test.txt", "Connection: " + connection);
			Util.writeToFile("test.txt", "Battery: " + Util.curBatt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(batteryBroadcast);
		super.onDestroy();
	}

	/* mBinder */
	public class MyBinder extends Binder {
		public RecordingService getService() {
			return RecordingService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
}
