package com.example2.school.myapplication;


/* this is the main activity */

/* import the relevant classes */

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.text.DateFormat.getDateTimeInstance;

public class MyActivity extends ActionBarActivity {


    private ListView listView; /* list variable */
    public static ArrayList<String> ArrayName = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        showInbox();  /* Display the Received messages */


    }



    @Override
    protected void onResume() {

        showInbox();  /*Display the sms*/
        super.onResume();
    }

    @Override
    protected void onPause() {

        showInbox();
        super.onPause();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }



    public void showInbox() {


        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayName);

        adapter1.clear();  /* clear the adapter/list content */

        listView.setAdapter(adapter1);

        SmsDatabase db;
        db = new SmsDatabase(MyActivity.this);

        List<Msg> list = db.getAllMsg();  /* retrieve all the sms in the database */


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArrayName);

        listView.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* the action bar */
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_sent) {

            showMyToast("Outgoing Messages");


        } else if (id == R.id.action_inbox) {

            showMyToast("Incoming Messages");

        }

        return super.onOptionsItemSelected(item);
    }




    public void showMyToast(String myString) {  //function that show/dispays a teast message

        Toast waitToast = Toast.makeText(getApplicationContext(),  myString, Toast.LENGTH_SHORT);
        waitToast.setGravity(Gravity.CENTER, 0, 10);
        waitToast.show();

    }


}



