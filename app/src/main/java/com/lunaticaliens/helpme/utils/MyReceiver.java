package com.lunaticaliens.helpme.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("Hello");
        Log.e(TAG, data + "");
        Log.e(TAG, intent.getAction());
        Log.e(TAG, "MyAction received!");
    }
}
