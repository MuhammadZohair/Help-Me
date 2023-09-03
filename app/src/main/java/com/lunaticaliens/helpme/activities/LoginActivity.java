package com.lunaticaliens.helpme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaticaliens.helpme.R;
import com.lunaticaliens.helpme.utils.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView createAccountTextView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeWidgets();
    }

    private void initializeWidgets() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountTextView = findViewById(R.id.createAccountTextView);

        loginButton.setOnClickListener(this);
        createAccountTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                if (databaseHelper.checkUser(email, password)) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("isLogin", true).apply();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("email", email).apply();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("userObject", databaseHelper.getUser(email));
                    startActivity(i);
                    finish();
                    databaseHelper.getUser(email).toString();
                } else Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Please fill both the fields to continue...", Toast.LENGTH_SHORT).show();
        }
        if (v == createAccountTextView) {
            Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(i);
            finish();
        }
    }
}
