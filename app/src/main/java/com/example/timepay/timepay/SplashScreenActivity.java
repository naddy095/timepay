package com.example.timepay.timepay;
 
import java.util.Timer;
import java.util.TimerTask;

import android.accounts.Account;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Window;

import android.telephony.TelephonyManager;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.example.Utils.SharedPreferenceHandler;


public class SplashScreenActivity extends Activity {
 
	// Set Duration of the Splash Screen
	long Delay = 8000;
	final Context context=this;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getIMEI();
        getMacAddress();
        getEmail(context);
        Log.i("Outputs","IMEI  " + SharedPreferenceHandler.readValue(this.context,"IMEI")+" MAC  "+SharedPreferenceHandler.readValue(this.context,"Mac Address")+"Email  " + SharedPreferenceHandler.readValue(this.context,"Synced Mail"));

		// Remove the Title Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get the view from splash_screen.xml
		setContentView(R.layout.splash_screen);
 
		// Create a Timer
		Timer RunSplash = new Timer();
 
		// Task to do when the timer ends
		//TimerTask ShowSplash = new TimerTask() {
			Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
                                            /*
                                             * We are creating this new thread because we don’t
                                             * want our main thread to stop working for that
                                             * time as our android stop working and some time
                                             * application will crashes
                                             */
					e.printStackTrace();
				}
				finally {
					Intent i = new Intent(SplashScreenActivity.this,
							Accounts.class);
					startActivity(i);
					finish();
				}

			}
		});
 
		// Start the timer
		//RunSplash.schedule(ShowSplash, Delay);
		th.start(); // start the thread
	}





	private String getIMEI() {


		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String imei=telephonyManager.getDeviceId();
        SharedPreferenceHandler.writeValue(this.context,"IMEI",imei);
        return imei;
	}

	private String getMacAddress() {
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		String macAddress = wInfo.getMacAddress();
        SharedPreferenceHandler.writeValue(this.context,"Mac Address",macAddress);
		return macAddress;
	}


	static String getEmail(Context context) {
		AccountManager accountManager = AccountManager.get(context);
		Account account = getAccount(accountManager);

		if (account == null) {
			return null;
		} else {
            SharedPreferenceHandler.writeValue(context,"Synced Mail",account.name);
			return account.name;
		}
	}

	private static Account getAccount(AccountManager accountManager) {
		Account[] accounts = accountManager.getAccountsByType("com.google");
		Account account;
		if (accounts.length > 0) {
			account = accounts[0];
		} else {
			account = null;
		}
		return account;
	}


}