package com.example.timepay.timepay;
 
import java.util.Properties;
import java.util.Timer;

import android.accounts.Account;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.Property;
import android.view.Window;

import android.telephony.TelephonyManager;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.example.Utils.FileIOHandler;
import com.example.Utils.PropertyHandler;
import com.example.Utils.SharedPreferenceHandler;
import com.example.componentservices.ComponentBaseServices;


public class SplashScreenActivity extends Activity {
 
	// Set Duration of the Splash Screen
	long Delay = 8000;
	final Context context=this;
    PropertyHandler propReader;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Properties myProperty;
        ComponentBaseServices baseServices = new ComponentBaseServices();
        baseServices.getIMEI(this.context);
        baseServices.getMacAddress(this.context);
        baseServices.getEmail(this.context);
        //Log.i("Outputs","IMEI  " + SharedPreferenceHandler.readValue(this.context,"IMEI")+" MAC  "+SharedPreferenceHandler.readValue(this.context,"Mac Address")+"Email  " + SharedPreferenceHandler.readValue(this.context,"Synced Mail"));
        Log.i("Outputs","IMEI  " + FileIOHandler.readValue(this.context,"IMEI")+" MAC  "+FileIOHandler.readValue(this.context,"Mac Address")+"Email  " + FileIOHandler.readValue(this.context,"Synced Mail"));
        propReader = new PropertyHandler(context);
        myProperty = propReader.getMyProperties("url.properties");
        Log.i("Sign in url :",myProperty.getProperty("signin"));
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
}