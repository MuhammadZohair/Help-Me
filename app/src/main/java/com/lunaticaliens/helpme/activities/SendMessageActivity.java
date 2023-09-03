package com.lunaticaliens.helpme.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaticaliens.helpme.R;
import com.lunaticaliens.helpme.models.Message;
import com.lunaticaliens.helpme.utils.DatabaseHelper;
import com.lunaticaliens.helpme.utils.Encryption;
import com.lunaticaliens.helpme.utils.MyReceiver;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SendMessageActivity";
    private static final int SOURCE_LOCATION_CODE = 888;
    private boolean locationChecked = false;

    private DatabaseHelper databaseHelper;

    private ImageView photoImageView;
    private EditText messageEditText;
    private CheckBox message1CheckBox;
    private CheckBox message2CheckBox;
    private CheckBox message3CheckBox;
    private CheckBox message4CheckBox;
    private CheckBox message5CheckBox;
    private TextView locationTextView;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        initializeWidgets();
    }

    private void initializeWidgets() {
        databaseHelper = new DatabaseHelper(getApplicationContext());

        photoImageView = findViewById(R.id.photoImageView);
        messageEditText = findViewById(R.id.messageEditText);
        message1CheckBox = findViewById(R.id.message1CheckBox);
        message2CheckBox = findViewById(R.id.message2CheckBox);
        message3CheckBox = findViewById(R.id.message3CheckBox);
        message4CheckBox = findViewById(R.id.message4CheckBox);
        message5CheckBox = findViewById(R.id.message5CheckBox);
        locationTextView = findViewById(R.id.locationTextView);
        sendMessageButton = findViewById(R.id.sendMessageButton);

        photoImageView.setOnClickListener(this);
        locationTextView.setOnClickListener(this);
        sendMessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == photoImageView) {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
        if (v == sendMessageButton) {
            if (!locationChecked) {
                String messageBody = messageEditText.getText().toString().trim();
                if (TextUtils.isEmpty(messageBody)) {
                    boolean oneChecked = false;
                    if (message1CheckBox.isChecked()) {
                        messageBody = message1CheckBox.getText().toString().trim();
                        oneChecked = true;
                    }
                    if (message2CheckBox.isChecked()) {
                        messageBody = message2CheckBox.getText().toString().trim();
                        oneChecked = true;
                    }
                    if (message3CheckBox.isChecked()) {
                        messageBody = message3CheckBox.getText().toString().trim();
                        oneChecked = true;
                    }
                    if (message4CheckBox.isChecked()) {
                        messageBody = message4CheckBox.getText().toString().trim();
                        oneChecked = true;
                    }
                    if (message5CheckBox.isChecked()) {
                        messageBody = message5CheckBox.getText().toString().trim();
                        oneChecked = true;
                    }
                    if (oneChecked) {
                        Toast.makeText(this, "Message Broadcast Successful", Toast.LENGTH_SHORT).show();
                        broadcastMessage(messageBody);
                        finish();
                    } else {
                        Toast.makeText(this, "Please select any message and set location", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Message Broadcast Successful", Toast.LENGTH_SHORT).show();
                    broadcastMessage(messageBody);
                    finish();
                }
            } else {
                Toast.makeText(this, "Please select location as well", Toast.LENGTH_SHORT).show();
            }
        }

        if (v == locationTextView) {
            locationChecked = true;
            startActivityForResult(new Intent(this, MapsActivity.class), SOURCE_LOCATION_CODE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SOURCE_LOCATION_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String street = data.getStringExtra("StreetName");
                Double lat = data.getDoubleExtra("Lat", 0);
                Double lng = data.getDoubleExtra("Lng", 0);
                locationTextView.setText(street + " " + lat + "," + lng);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                photoImageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.e(TAG, error.toString());
            }
        }
    }

    private void broadcastMessage(String mess) {
        Message message = new Message();
        message.setMessage(mess);
        photoImageView.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) photoImageView.getDrawable();
        message.setPhoto(Encryption.encodePicture(drawable.getBitmap()));
        message.setUserID(1);

        sendBroadcast();

        databaseHelper.insertMessage(message);
    }

    public void sendBroadcast() {
        Intent intent = new Intent(this, MyReceiver.class);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("Hello", "Hello");
        intent.setComponent(new
                ComponentName("com.lunaticaliens.helpme",
                "com.lunaticaliens.helpme.utils.MyReceiver"));
        sendBroadcast(new Intent(intent).setAction("MyAction"));
    }


}
