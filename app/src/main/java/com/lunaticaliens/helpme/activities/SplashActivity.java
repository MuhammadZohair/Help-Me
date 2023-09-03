package com.lunaticaliens.helpme.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lunaticaliens.helpme.R;
import com.lunaticaliens.helpme.utils.DatabaseHelper;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private DatabaseHelper databaseHelper;

    /**
     * This method is called when the activity is created
     *
     * @param savedInstanceState none
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //TODO CHANGE THIS to NOT
                if (isNetworkAvailable()) {
                    boolean isLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("isLogin", false);
                    if (isLogin) {
                        String email = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("email", "email");
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        i.putExtra("userObject", databaseHelper.getUser(email));
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(SplashActivity.this).create();
                    alertDialog.setTitle(getResources().getString(R.string.app_name));
                    alertDialog.setMessage("Internet connection detected, app won't work");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Okay",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toast.makeText(SplashActivity.this, "Application is terminating", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Disable and Proceed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (isNetworkAvailable()) {
                                Toast.makeText(SplashActivity.this, "Network still available, Application is terminating", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                    alertDialog.show();
                }

            }
        }, 1000);
    }

    /**
     * This method checks if the network is available or not
     *
     * @return network availability
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
