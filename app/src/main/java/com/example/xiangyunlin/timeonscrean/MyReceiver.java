package com.example.xiangyunlin.timeonscrean;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.xiangyunlin.timeonscreen.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    private TextView mTimeText;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public MyReceiver(TextView timeText) {
        mTimeText = timeText;
    }

    public MyReceiver() {

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if( intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            Log.d("test", sdf.format(new Date()));
            mTimeText.setText(sdf.format(new Date()));
            Log.d("test", "12132132132132");
        }

    }
}
