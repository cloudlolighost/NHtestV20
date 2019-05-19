package com.example.nhtestv10;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class clock extends AppCompatActivity {
    private TextView setTime1, setTime2;
    private Button mButton1, mButton2, mButton3, mButton4;
    private Calendar c = Calendar.getInstance();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_refresh: //點了重新整理
                //loadData();
                return true;
            case R.id.action_settings: //點了settings
                Log.d("item","click settings");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 只響一次鬧钟設置
        setTime1 = (TextView) findViewById(R.id.setTime1);
        mButton1 = (Button) findViewById(R.id.mButton1);
        mButton2 = (Button) findViewById(R.id.mButton2);





        mButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 取得單擊按鈕時的時間作为TimePickerDialog的默認值
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);


                // 跳出TimePickerDialog來設置時間
                new TimePickerDialog(clock.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                // TODO Auto-generated method stub
                                // 取得設置後的時間，秒跟毫秒設为0
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);

                                // 指定鬧钟設置的時間到時，要運行的CallAlarm.class
                                Intent intent = new Intent(
                                        clock.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent
                                        .getBroadcast(
                                                clock.this,
                                                0,
                                                intent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
                                // AlarmManaer.RTC_WAKEUP設置服務在系統休眠時同样會運行
                                // 以set()設置的PendingIntent只會運行一次
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                am.set(AlarmManager.RTC_WAKEUP,
                                        c.getTimeInMillis(), sender);
                                // 更新顯示的設置鬧钟時間
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime1.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧钟時間为：" + tmpS, Toast.LENGTH_LONG)
                                        .show();
                            }
                        }, mHour, mMinute, false).show();
            }
        });
        // 只響一次的鬧钟刪除
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(clock.this,
                        CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        clock.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(clock.this, "鬧钟已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime1.setText("目前無設置！");
            }
        });
        // 重复響起的鬧钟設置/////////////////////////////////
        // 重复響起的鬧钟的設置畫面，引用timeset.xml
        /*LayoutInflater factory = LayoutInflater.from(this);
        final View setView = factory.inflate(R.layout.timeset, null);
        final TimePicker tPicker = (TimePicker) setView
                .findViewById(R.id.tPicker);
        tPicker.setIs24HourView(true);
        final AlertDialog di = new AlertDialog.Builder(clock.this)
                .setIcon(R.drawable.iconfinder_clock_322431).setTitle("設置").setView(setView)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        // 取得設置的間隔秒數
                        EditText ed = (EditText) setView
                                .findViewById(R.id.mEdit);
                        int times = Integer.parseInt(ed.getText().toString()) * 1000;
                        // 取得設置後的時間，秒跟毫秒設为0
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR_OF_DAY, tPicker.getHour());
                        c.set(Calendar.MINUTE, tPicker.getMinute());
                        c.set(Calendar.SECOND, 0);
                        c.set(Calendar.MILLISECOND, 0);
                        // 指定鬧钟設置的時間到時，要運行的CallAlarm.class
                        Intent intent = new Intent(clock.this,
                                CallAlarm.class);
                        PendingIntent sender = PendingIntent.getBroadcast(
                                clock.this, 1, intent, 0);
                        // setReapting()可讓鬧钟重复運行
                        AlarmManager am;
                        am = (AlarmManager) getSystemService(ALARM_SERVICE);
                        am.setRepeating(AlarmManager.RTC_WAKEUP,
                                c.getTimeInMillis(), times, sender);
                        // 更新顯示的設置鬧钟時間
                        String tmpS = format(tPicker.getHour()) + ":"
                                + format(tPicker.getMinute());
                        setTime2.setText(tmpS);
                        Toast.makeText(
                                clock.this,
                                "設置的鬧钟時間为：" + tmpS + "\n重复時間間隔是:" + times
                                        / 1000+"秒", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                }).create();

        // 重复響起鬧钟設置的按鈕
        mButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 取得單擊按鈕時的時間作为tPicker的默認值
                c.setTimeInMillis(System.currentTimeMillis());
                tPicker.setHour(c.get(Calendar.HOUR_OF_DAY));
                tPicker.setMinute(c.get(Calendar.MINUTE));
                di.show();
            }
        });

        // 重复響的鬧钟 刪除
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(clock.this,
                        CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        clock.this, 1, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(clock.this, "鬧钟已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime2.setText("目前無設置！");
            }
        });*/
    }

    private String format(int x) {
        // TODO Auto-generated method stub
        String s = "" + x;
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
}

