package com.lunaticaliens.helpme.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lunaticaliens.helpme.R;
import com.lunaticaliens.helpme.models.Message;
import com.lunaticaliens.helpme.utils.DatabaseHelper;
import com.lunaticaliens.helpme.utils.MyReceiver;
import com.lunaticaliens.helpme.utils.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    private static final String TAG = "FriendsActivity";
    private MyRecyclerViewAdapter adapter;
    private ArrayList<Message> messageArrayList;
    private DatabaseHelper databaseHelper;
    private MyReceiver myReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

//        databaseHelper = new DatabaseHelper(getApplicationContext());
//        messageArrayList = databaseHelper.getAllMessages();
//
//        ArrayList<Message> newArray = new ArrayList<>();
//
//        for (int i = 0; i < messageArrayList.size(); i++) {
//            newArray.add(messageArrayList.get(i));
//        }
//
//        // set up the RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.recycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyRecyclerViewAdapter(this, newArray);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);

        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("MyAction");
        this.registerReceiver(myReceiver, filter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Clicked at: " + messageArrayList.get(position).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
