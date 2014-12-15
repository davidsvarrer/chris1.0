package com.example2.school.myapplication;

/* import the relevant classes */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * this is the db handler
 */
public class SmsDatabase extends SQLiteOpenHelper {

    /*Database Version*/
    private static final int DATABASE_VERSION = 1;
    /*Database Name*/
    private static final String DATABASE_NAME = "SmsStore4";

    public SmsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /* SQL statement to create received_td table */
        String CREATE_RECEIVED_TABLE = "CREATE TABLE received_td ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sender_no VARCHAR, "+
                "sms_body TEXT, "+
                "sms_date VARCHAR )";

        /* create received_td table */
        db.execSQL(CREATE_RECEIVED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older received_td table if existed
        db.execSQL("DROP TABLE IF EXISTS received_td");

        // create fresh received_td table
        this.onCreate(db);
    }


    // received_td table name
    private static final String TABLE_RECEIVED = "received_td";

    // received_td Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SENDER = "sender_no";
    private static final String KEY_BODY = "sms_body";
    private static final String KEY_DATE = "sms_date";

    private static final String[] COLUMNS = {KEY_ID, KEY_SENDER, KEY_BODY, KEY_DATE};


/* add/insert the sms to the received_td */
    public void addSMS(Msg msg){

        /* for logging */
        Log.d("addSMS", msg.toString());

        // 1. get reference to Writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_SENDER, msg.getSender()); // SMS SENDER
        values.put(KEY_BODY, msg.getBody()); // get SMS BODY
        values.put(KEY_DATE, msg.getDate()); // get SMS DATE

        // 3. insert
        db.insert(TABLE_RECEIVED, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        /* 4. close */
        db.close();
    }


    /* get all received sms from the received_td */
    public List<Msg> getAllMsg() {

        List<Msg> theMessage = new ArrayList<Msg>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_RECEIVED + " ORDER BY id DESC";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        /* 3. go over each row, add the sms/s to list */
        Msg msg = null;
        if (cursor.moveToFirst()) {
            do {
                msg = new Msg();
                msg.setId(Integer.parseInt(cursor.getString(0)));
                msg.setSender(cursor.getString(1));
                msg.setBody(cursor.getString(2));
                msg.setDate(cursor.getString(3));

                String namez = "The ID "+cursor.getString(0) +"\nThe Sender "+ cursor.getString(1) +"\nThe Message "+ cursor.getString(2)+ "\nReceived on  " + cursor.getString(3);


                MyActivity.ArrayName.add(namez);
                theMessage.add(msg);
            } while (cursor.moveToNext());
        }

        Log.d("getAllSMS()", theMessage.toString());


        return theMessage;

    }




    /* sent sms still working on it*/





}
