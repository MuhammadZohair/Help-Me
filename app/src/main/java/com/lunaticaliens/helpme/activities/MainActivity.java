package com.lunaticaliens.helpme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lunaticaliens.helpme.R;
import com.lunaticaliens.helpme.models.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button victimButton;
    private Button friendsButton;
    private Button helperButton;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentUser = (User) getIntent().getSerializableExtra("userObject");
        Log.e(TAG, currentUser.toString());

        initializeWidgets();
    }

    private void initializeWidgets() {
        victimButton = findViewById(R.id.victimButton);
        victimButton.setOnClickListener(this);

        friendsButton = findViewById(R.id.friendsButton);
        friendsButton.setOnClickListener(this);

        helperButton = findViewById(R.id.helperButton);
        helperButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == victimButton) {
            startActivity(new Intent(this, SendMessageActivity.class));
        }

        if (v == friendsButton) {
            startActivity(new Intent(this, FriendsActivity.class));
        }

        if (v == helperButton) {
            startActivity(new Intent(this, HelperActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
