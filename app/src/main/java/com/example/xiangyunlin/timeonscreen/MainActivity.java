package com.example.xiangyunlin.timeonscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private static BroadcastReceiver tickReceiver;
    private TextView timeText;
    private SimpleDateFormat date12SDF = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    private SimpleDateFormat date24SDF = new SimpleDateFormat("HH:mm");
//    private MyReceiver tickReceiver;
    private Button AMbtn;
    private Button PMbtn;
    boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMbtn = (Button) findViewById(R.id.date12btn);
        PMbtn = (Button) findViewById(R.id.date24btn);

        AMbtn.setOnClickListener(this);
        PMbtn.setOnClickListener(this);

        timeText = (TextView) findViewById(R.id.timeText);
        timeText.setText(date24SDF.format(new Date()));
        timeText.setTextSize(40);

        /*
        Intent intent = new Intent(MainActivity.this, MyReceiver.class);
        tickReceiver = new MyReceiver();
        */

        tickReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                if( intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0 ) {
                    timeText.setText(date24SDF.format(new Date()));
                }
            }
        };
    }

    // click btn to show 12 or 24 hrs setting
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date12btn:
                timeText.setText(date12SDF.format(new Date()));
                break;
            case R.id.date24btn:
                timeText.setText(date24SDF.format(new Date()));
                break;
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        //unregister broadcast receiver.
        if( tickReceiver != null )
            unregisterReceiver(tickReceiver);
        Log.d("xiang_test", "2222222222222222222222");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //register broadcast receiver.
        registerReceiver(tickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("xiang_test", "11111111111");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("xiang_test", "3333333333333");
    }


}
