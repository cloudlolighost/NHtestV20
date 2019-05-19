package com.example.nhtestv10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.media.RingtoneManager.TYPE_ALARM;

//鬧鐘接收器
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {
// TODO Auto-generated method stub
        Toast.makeText(arg0, "時間到了該吃藥囉~~~", Toast.LENGTH_LONG).show();
    }
}
