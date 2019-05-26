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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class clock extends AppCompatActivity {
    private TextView setTime1, setTime2,setTime3,setTime4;
    private Button mButton1, mButton2, mButton3, mButton4,mButton5,mButton6,mButton7,mButton8;
    private Calendar c = Calendar.getInstance();
    private Calendar d = Calendar.getInstance();
    private Calendar a = Calendar.getInstance();
    private Calendar b = Calendar.getInstance();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent intent = new Intent();
                intent.setClass(clock.this , MainActivity.class);
                startActivity(intent);
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();



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

        // Read from the database
        DatabaseReference load=myRef.child("Member");
        load.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String loadclock1=dataSnapshot.child("clock1").child("hour").getValue().toString()+":"+dataSnapshot.child("clock1").child("minute").getValue().toString();
                String loadclock2=dataSnapshot.child("clock2").child("hour").getValue().toString()+":"+dataSnapshot.child("clock2").child("minute").getValue().toString();
                String loadclock3=dataSnapshot.child("clock3").child("hour").getValue().toString()+":"+dataSnapshot.child("clock3").child("minute").getValue().toString();
                String loadclock4=dataSnapshot.child("clock4").child("hour").getValue().toString()+":"+dataSnapshot.child("clock4").child("minute").getValue().toString();
                if (loadclock1.equals("0:0")){
                    setTime1.setText("目前無設置！");
                }
                else{setTime1.setText(loadclock1);}
                if (loadclock2.equals("0:0")){
                    setTime2.setText("目前無設置！");
                }
                else{setTime2.setText(loadclock2);}
                if (loadclock3.equals("0:0")){
                    setTime3.setText("目前無設置！");
                }
                else{setTime3.setText(loadclock3);}
                if (loadclock4.equals("0:0")){
                    setTime4.setText("目前無設置！");
                }
                else{setTime4.setText(loadclock4);}


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


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
                                myRef.child("Member").child("clock1").child("hour").setValue(hourOfDay);
                                myRef.child("Member").child("clock1").child("minute").setValue(minute);
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime1.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧鐘時間為：" + tmpS, Toast.LENGTH_LONG)
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
                Toast.makeText(clock.this, "鬧鐘已刪除！",
                        Toast.LENGTH_LONG).show();
                myRef.child("Member").child("clock1").child("hour").setValue(0);
                myRef.child("Member").child("clock1").child("minute").setValue(0);
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
                                myRef.child("Member").child("clock2").child("hour").setValue(hourOfDay);
                                myRef.child("Member").child("clock2").child("minute").setValue(minute);
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime2.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧鐘時間為：" + tmpS, Toast.LENGTH_LONG)
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
                Toast.makeText(clock.this, "鬧鐘已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime2.setText("目前無設置！");
                myRef.child("Member").child("clock2").child("hour").setValue(0);
                myRef.child("Member").child("clock2").child("minute").setValue(0);
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
                                myRef.child("Member").child("clock3").child("hour").setValue(hourOfDay);
                                myRef.child("Member").child("clock3").child("minute").setValue(minute);
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime3.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧鐘時間為：" + tmpS, Toast.LENGTH_LONG)
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
                Toast.makeText(clock.this, "鬧鐘已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime3.setText("目前無設置！");
                myRef.child("Member").child("clock3").child("hour").setValue(0);
                myRef.child("Member").child("clock3").child("minute").setValue(0);
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
                                myRef.child("Member").child("clock4").child("hour").setValue(hourOfDay);
                                myRef.child("Member").child("clock4").child("minute").setValue(minute);
                                String tmpS = format(hourOfDay) + ":"
                                        + format(minute);
                                setTime4.setText(tmpS);
                                Toast.makeText(clock.this,
                                        "設置的鬧鐘時間為：" + tmpS, Toast.LENGTH_LONG)
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
                Toast.makeText(clock.this, "鬧鐘已刪除！",
                        Toast.LENGTH_LONG).show();
                setTime4.setText("目前無設置！");
                myRef.child("Member").child("clock4").child("hour").setValue(0);
                myRef.child("Member").child("clock4").child("minute").setValue(0);
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();

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

