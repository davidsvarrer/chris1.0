package com.example2.school.myapplication;

/**
 * Created by Chris
 * database getters & setters
 *the is the class that handles the database
 */
public class Msg {


   /* declare the variables */
    private int id;
    private String sender_no;
    private String sms_body;
    private String sms_date;

    public Msg(){}

    public Msg(String sender_no, String sms_body, String sms_date) {

        super();
        this.sender_no = sender_no;
        this.sms_body = sms_body;
        this.sms_date = sms_date;

    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender_no;
    }
    public void setSender(String sender_no) {
        this.sender_no = sender_no;
    }

    public String getBody() {
        return sms_body;
    }
    public void setBody(String sms_body) {
        this.sms_body = sms_body;
    }

    public String getDate() {
        return sms_date;
    }
    public void setDate(String sms_date) {
        this.sms_date = sms_date;
    }



    @Override
    public String toString() {
        return "Sms [id=" + id + ", Sender=" + sender_no + ", Message=" + sms_body + ", Date=" + sms_date + "]";
    }

}
