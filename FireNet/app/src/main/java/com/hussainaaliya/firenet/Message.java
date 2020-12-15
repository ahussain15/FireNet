package com.hussainaaliya.firenet;

import java.util.Date;

public class Message {

    private String message_text;
    private String message_user;
    private double message_time;

    public Message(){
        message_text = "";
        message_user = "";
        message_time = new Date().getTime();
    }

    public Message(String text, String user){
        message_text = text;
        int i = user.indexOf("@");
        if(i != -1)
            user = user.substring(0, i);
        message_user = user;
        message_time = new Date().getTime();
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getMessage_user() {
        return message_user;
    }

    public void setMessage_user(String message_user) {
        this.message_user = message_user;
    }

    public double getMessage_time() {
        return message_time;
    }

    public void setMessage_time(double message_time) {
        this.message_time = message_time;
    }
}
