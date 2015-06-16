package com.example.componentservices;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.example.Utils.FileIOHandler;

/**
 * Created by nadeem on 14-06-2015.
 * All the data related to user device goes in here. eg - IMEI number etc.
 */
public class ComponentBaseServices {

    public static String getIMEI(Context context){
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei=telephonyManager.getDeviceId();
        //SharedPreferenceHandler.writeValue(this.context,"IMEI",imei);
        FileIOHandler.writeValue(context, "IMEI", imei);
        return imei;
    }

    public static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        //SharedPreferenceHandler.writeValue(this.context,"Mac Address",macAddress);
        FileIOHandler.writeValue(context,"Mac Address",macAddress);
        return macAddress;
    }

    public static String getEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);
        if (account == null) {
            return null;
        } else {
            //SharedPreferenceHandler.writeValue(context,"Synced Mail",account.name);
            FileIOHandler.writeValue(context, "Synced Mail", account.name);
            return account.name;
        }
    }

    protected static Account getAccount(AccountManager accountManager) {
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
