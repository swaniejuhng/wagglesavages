package com.example.android.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SocketTest socketTest = new SocketTest();

        //String [] args = {"."};
        //String str = socketTest.main(args);
        //Intent intent = getIntent();
        //TextView tv = (TextView) findViewById(R.id.textView);
        //tv.setText(str);

        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Waggle_List.class);
                startActivity(intent);
            }
        });

    } // end of onCreate
}
