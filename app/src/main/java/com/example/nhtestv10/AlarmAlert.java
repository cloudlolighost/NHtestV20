package com.example.nhtestv10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlarmAlert extends Activity {
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        new AlertDialog.Builder(AlarmAlert.this).setIcon(R.drawable.iconfinder_clock_322431)
                .setTitle("鬧钟響了。。。").setMessage("記得該吃藥囉！")
                .setPositiveButton("關閉", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        AlarmAlert.this.finish();
                    }
                }).show();
    }
}
