package com.example.swanie.myapplication;

import android.app.Activity;

import java.util.ArrayList;

public class Waggle_List extends Activity {

    public static void main (String []args) {
        SocketTest st = new SocketTest();
        ArrayList arr = st.runClient("waggle");
        // then go to detail page
    }

}