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
    private TextView timeText;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if( intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0 ) {
            Log.d("test", sdf.format(new Date()));
            timeText.setText(sdf.format(new Date()));
            Log.d("test", "12132132132132");
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
