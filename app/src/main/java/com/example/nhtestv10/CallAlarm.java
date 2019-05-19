package com.example.nhtestv10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class CallAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Intent i = new Intent(context, AlarmAlert.class);
        Bundle bundleRet = new Bundle();
        bundleRet.putString("SRC_CALLER", "");
        i.putExtras(bundleRet);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
