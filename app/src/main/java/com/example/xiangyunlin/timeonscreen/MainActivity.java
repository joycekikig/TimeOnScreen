package com.example.xiangyunlin.timeonscreen;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiangyunlin.timeonscrean.MyReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
//    private static BroadcastReceiver tickReceiver;
    private TextView timeText;
    private SimpleDateFormat date12SDF = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    private SimpleDateFormat date24SDF = new SimpleDateFormat("HH:mm");
    private MyReceiver tickReceiver;
    private Button AMbtn;
    private Button PMbtn;
    boolean isdate12Pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMbtn = (Button) findViewById(R.id.date12btn);
        PMbtn = (Button) findViewById(R.id.date24btn);

        AMbtn.setOnClickListener(this);
        PMbtn.setOnClickListener(this);

        timeText = (TextView) findViewById(R.id.timeText);


        //Intent intent = new Intent(MainActivity.this, MyReceiver.class);
        tickReceiver = new MyReceiver(this);

        /*
        tickReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                if( intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0 ) {
                    timeText.setText(date24SDF.format(new Date()));
                }
            }
        };
        */
    }

    // click btn to show 12 or 24 hrs setting
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date12btn:
                isdate12Pressed = true;
                date12SDFSetting();
                Log.d("xiang_test", "You push 12hr setting !");
                break;
            case R.id.date24btn:
                isdate12Pressed = false;
                date24SDFSetting();
                Log.d("xiang_test", "You push 24hr setting !");
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
        Log.d("xiang_test", "You push onStop !");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Use the SharedPreferences to save the user last setting (like config idea)
        if(isdate12Pressed)
            getSharedPreferences("hrSetting", 0).edit().putBoolean("date12SDF", true).apply();
        else
            getSharedPreferences("hrSetting", 0).edit().putBoolean("date12SDF", false).apply();
        Log.d("xiang_test", "You push onPause !");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("xiang_test", "You push onDestroy !");
    }

    @Override
    protected void onResume() {
        super.onResume();

        //register broadcast receiver.
        registerReceiver(tickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));

        // Use the SharedPreferences to read the user last setting
        isdate12Pressed = getSharedPreferences("hrSetting", 0).getBoolean("date12SDF", true);

        if(isdate12Pressed)
            date12SDFSetting();
        else
            date24SDFSetting();
    }

    public void date12SDFSetting() {
        timeText.setText(date12SDF.format(new Date()));
        timeText.setTextSize(40);
    }

    public void date24SDFSetting() {
        timeText.setText(date24SDF.format(new Date()));
        timeText.setTextSize(40);
    }

    // get the textView
    public TextView getTimeText() {
        return timeText;
    }

    // get the isdate12Pressed
    public boolean getisdate12Pressed() {
        return isdate12Pressed;
    }
}
