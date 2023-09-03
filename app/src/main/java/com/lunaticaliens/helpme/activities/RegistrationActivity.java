package com.lunaticaliens.helpme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaticaliens.helpme.R;
import com.lunaticaliens.helpme.models.User;
import com.lunaticaliens.helpme.utils.DatabaseHelper;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegistrationActivity";

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText phoneNumberEditText;
    private EditText usernameEditText;

    private Button createAccountButton;

    private TextView loginTextView;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initializeWidgets();
    }

    private void initializeWidgets() {
        databaseHelper = new DatabaseHelper(getApplicationContext());

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        createAccountButton = findViewById(R.id.createAccountButton);
        loginTextView = findViewById(R.id.loginTextView);

        createAccountButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == createAccountButton) {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String phoneNumber = phoneNumberEditText.getText().toString().trim();
            String username = usernameEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) 
                    && !TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(username)){
                databaseHelper.insertUser(new User(username, email,password, Long.parseLong(phoneNumber)));
                Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            else
                Toast.makeText(this, "Please fill all the fields to continue", Toast.LENGTH_SHORT).show();
        }
        if (v == loginTextView) {
            Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

}
