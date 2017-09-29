package com.example.xiangyunlin.timeonscrean;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.xiangyunlin.timeonscreen.MainActivity;
import com.example.xiangyunlin.timeonscreen.R;

public class MyReceiver extends BroadcastReceiver {
    MainActivity mActivity;

    public MyReceiver() {

    }

    public MyReceiver(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if( intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            Log.d("test", mActivity.getTimeText().getText().toString());
            if(mActivity.getisdate12Pressed())
                mActivity.date12SDFSetting();
            else
                mActivity.date24SDFSetting();
        }
    }
}
