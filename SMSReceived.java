package com.example2.school.myapplication;

/* now this is the main course */

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.PendingIntent.getActivity;
import static java.text.DateFormat.getDateTimeInstance;


public class SMSReceiver extends BroadcastReceiver {  /*am using the BroadcastReceiver class*/

/*  This is the sms Received Receiver handler */

    @Override
    public void onReceive(Context context, Intent intent)
    {
       /*---get the SMS message passed in---*/

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        /* Define the variables */

        String str = "";
        String senderNo = "";
        String msgBody = "";
        String msgDate = "";

        if (bundle != null)
        {
            /*--- retrieve the SMS message received ---*/
            Object[] pdus = (Object[]) bundle.get("pdus");

            msgs = new SmsMessage[pdus.length];

            for (int i=0; i<msgs.length; i++){

                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

                senderNo = msgs[i].getOriginatingAddress(); /* get the sms address(Number) */
                msgBody = msgs[i].getMessageBody().toString(); /* get the sms content(Body) */
                msgDate = getDateTimeInstance().format(Calendar.getInstance().getTime()); /* get the current time/date */

            }

            /* insert the message into the received td */

          /* i call the assSMS method from the SmsDatabase class */
            SmsDatabase db2;
            db2 = new SmsDatabase(context);
            db2.addSMS(new Msg(senderNo, msgBody, msgDate)); /* add sms detals to database */



        }
    }


}
