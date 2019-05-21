package com.example.nhtestv10;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
    private TextView setTime1, setTime2,setTime3,setTime4;
    private Button mButton1, mButton2, mButton3, mButton4,mButton5,mButton6,mButton7,mButton8;
    private Calendar c = Calendar.getInstance();
    private Calendar d = Calendar.getInstance();
    private Calendar a = Calendar.getInstance();
    private Calendar b = Calendar.getInstance();


/*    SharedPreferences clock1 = getSharedPreferences("clock1", 0);
    Integer str1 = clock1.getInt("hour",0);
    Integer str2 = clock1.getInt("minute",0);
    SharedPreferences clock2= getSharedPreferences("clock2", 0);
    Integer str3 = clock2.getInt("hour",0);
    Integer str4 = clock2.getInt("minute",0);
    SharedPreferences clock3 = getSharedPreferences("clock3", 0);
    Integer str5 = clock3.getInt("hour",0);
    Integer str6 = clock3.getInt("minute",0);
    SharedPreferences clock4= getSharedPreferences("clock4", 0);
    Integer str7 = clock4.getInt("hour",0);
    Integer str8 = clock4.getInt("minute",0);*/







    /* 自建的資料庫類別 */
    /*private MyDB db = null;*/

    /* 資料表欄位 */
// private final static String _ID = "_id";
// private final static String NAME = "name";
// private final static String PRICE = "price";
    /*Cursor cursor;
    long myid; // 儲存 _id 的值*/

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
        setTime2 = (TextView) findViewById(R.id.setTime2);
        setTime3 = (TextView) findViewById(R.id.setTime3);
        setTime4 = (TextView) findViewById(R.id.setTime4);
        mButton1 = (Button) findViewById(R.id.mButton1);
        mButton2 = (Button) findViewById(R.id.mButton2);
        mButton3 = (Button) findViewById(R.id.mButton3);
        mButton4 = (Button) findViewById(R.id.mButton4);
        mButton5 = (Button) findViewById(R.id.mButton5);
        mButton6 = (Button) findViewById(R.id.mButton6);
        mButton7 = (Button) findViewById(R.id.mButton7);
        mButton8 = (Button) findViewById(R.id.mButton8);

        /*String tmpS1 = format(str1) + ":"
                + format(str2);
        setTime1.setText(tmpS1);
        String tmpS2 = format(str1) + ":"
                + format(str2);
        setTime2.setText(tmpS2);
        String tmpS3 = format(str1) + ":"
                + format(str2);
        setTime3.setText(tmpS3);
        String tmpS4 = format(str1) + ":"
                + format(str2);
        setTime4.setText(tmpS4);*/





        /*db = new MyDB(this); // 建立 MyDB 物件
        db.open();
        cursor = db.getAll();// 載入全部資料*/


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
                                //str1=hourOfDay;
                                //str2=minute;
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
        mButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 取得單擊按鈕時的時間作为TimePickerDialog的默認值
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = d.get(Calendar.HOUR_OF_DAY);
                int mMinute = d.get(Calendar.MINUTE);


                // 跳出TimePickerDialog來設置時間
                new TimePickerDialog(clock.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                // TODO Auto-generated method stub
                                // 取得設置後的時間，秒跟毫秒設为0
                                d.setTimeInMillis(System.currentTimeMillis());
                                d.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                d.set(Calendar.MINUTE, minute);
                                d.set(Calendar.SECOND, 0);
                                d.set(Calendar.MILLISECOND, 0);

                                // 指定鬧钟設置的時間到時，要運行的CallAlarm.class
                                Intent intent = new Intent(
                                        clock.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent
                                        .getBroadcast(
                                                clock.this,
                                                1,
                                                intent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
                                // AlarmManaer.RTC_WAKEUP設置服務在系統休眠時同样會運行
                                // 以set()設置的PendingIntent只會運行一次
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                am.set(AlarmManager.RTC_WAKEUP,
                                        d.getTimeInMillis(), sender);
                                // 更新顯示的設置鬧钟時間
                                //str3=hourOfDay;
                                //str4=minute;
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime2.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧钟時間为：" + tmpS, Toast.LENGTH_LONG)
                                        .show();
                            }
                        }, mHour, mMinute, false).show();
            }
        });
        // 只響一次的鬧钟刪除
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
        });
        mButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 取得單擊按鈕時的時間作为TimePickerDialog的默認值
                a.setTimeInMillis(System.currentTimeMillis());
                int mHour = a.get(Calendar.HOUR_OF_DAY);
                int mMinute = a.get(Calendar.MINUTE);


                // 跳出TimePickerDialog來設置時間
                new TimePickerDialog(clock.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                // TODO Auto-generated method stub
                                // 取得設置後的時間，秒跟毫秒設为0
                                a.setTimeInMillis(System.currentTimeMillis());
                                a.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                a.set(Calendar.MINUTE, minute);
                                a.set(Calendar.SECOND, 0);
                                a.set(Calendar.MILLISECOND, 0);

                                // 指定鬧钟設置的時間到時，要運行的CallAlarm.class
                                Intent intent = new Intent(
                                        clock.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent
                                        .getBroadcast(
                                                clock.this,
                                                2,
                                                intent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
                                // AlarmManaer.RTC_WAKEUP設置服務在系統休眠時同样會運行
                                // 以set()設置的PendingIntent只會運行一次
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                am.set(AlarmManager.RTC_WAKEUP,
                                        a.getTimeInMillis(), sender);
                                // 更新顯示的設置鬧钟時間
                                //str5=hourOfDay;
                                //str6=minute;
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime3.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧钟時間为：" + tmpS, Toast.LENGTH_LONG)
                                        .show();
                            }
                        }, mHour, mMinute, false).show();
            }
        });
        // 只響一次的鬧钟刪除
        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(clock.this,
                        CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        clock.this, 2, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(clock.this, "鬧钟已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime3.setText("目前無設置！");
            }
        });
        mButton7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 取得單擊按鈕時的時間作为TimePickerDialog的默認值
                b.setTimeInMillis(System.currentTimeMillis());
                int mHour = b.get(Calendar.HOUR_OF_DAY);
                int mMinute = b.get(Calendar.MINUTE);


                // 跳出TimePickerDialog來設置時間
                new TimePickerDialog(clock.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                // TODO Auto-generated method stub
                                // 取得設置後的時間，秒跟毫秒設为0
                                b.setTimeInMillis(System.currentTimeMillis());
                                b.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                b.set(Calendar.MINUTE, minute);
                                b.set(Calendar.SECOND, 0);
                                b.set(Calendar.MILLISECOND, 0);

                                // 指定鬧钟設置的時間到時，要運行的CallAlarm.class
                                Intent intent = new Intent(
                                        clock.this, CallAlarm.class);
                                PendingIntent sender = PendingIntent
                                        .getBroadcast(
                                                clock.this,
                                                3,
                                                intent,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
                                // AlarmManaer.RTC_WAKEUP設置服務在系統休眠時同样會運行
                                // 以set()設置的PendingIntent只會運行一次
                                AlarmManager am;
                                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                                am.set(AlarmManager.RTC_WAKEUP,
                                        b.getTimeInMillis(), sender);
                                // 更新顯示的設置鬧钟時間
                                //str7=hourOfDay;
                                //str8=minute;
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime4.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧钟時間为：" + tmpS, Toast.LENGTH_LONG)
                                        .show();
                            }
                        }, mHour, mMinute, false).show();
            }
        });
        // 只響一次的鬧钟刪除
        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(clock.this,
                        CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        clock.this, 3, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(clock.this, "鬧钟已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime4.setText("目前無設置！");
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();

        /*SharedPreferences clock1 = getSharedPreferences("clock1", 0);
        SharedPreferences.Editor editor = clock1.edit();

        editor.putInt("hour",str1);
        editor.putInt("minute",str2);
        editor.apply();
        SharedPreferences clock2 = getSharedPreferences("clock2", 0);
        SharedPreferences.Editor editor2 = clock2.edit();

        editor2.putInt("hour",str3);
        editor2.putInt("minute",str4);
        editor2.apply();
        SharedPreferences clock3 = getSharedPreferences("clock1", 0);
        SharedPreferences.Editor editor3 = clock3.edit();

        editor3.putInt("hour",str5);
        editor3.putInt("minute",str6);
        editor3.apply();
        SharedPreferences clock4 = getSharedPreferences("clock1", 0);
        SharedPreferences.Editor editor4 = clock4.edit();

        editor4.putInt("hour",str7);
        editor4.putInt("minute",str8);
        editor4.apply();*/
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

