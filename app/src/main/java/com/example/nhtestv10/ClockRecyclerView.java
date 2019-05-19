package com.example.nhtestv10;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ClockRecyclerView extends AppCompatActivity {
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_recycler_view);
        ArrayList<String> myDataset = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            myDataset.add(Integer.toString(i));
        }
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<String> mData;
        public String tmpS;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.info_text);

            }
            public void setView(String input){
                mTextView.setText(input);

            }
        }

        public MyAdapter(ArrayList<String> data) {
            mData = data;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mTextView.setText(tmpS);



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    // 取得單擊按鈕時的時間作为TimePickerDialog的默認值
                    c.setTimeInMillis(System.currentTimeMillis());
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);


                    // 跳出TimePickerDialog來設置時間
                    new TimePickerDialog(ClockRecyclerView.this,
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
                                            ClockRecyclerView.this, CallAlarm.class);
                                    PendingIntent sender = PendingIntent
                                            .getBroadcast(
                                                    ClockRecyclerView.this,
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
                                    tmpS = format(hourOfDay) + ":"
                                            + format(minute);


                                    Toast.makeText(ClockRecyclerView.this,
                                            "設置的鬧钟時間为：" + tmpS, Toast.LENGTH_LONG)
                                            .show();
                                }
                            }, mHour, mMinute, false).show();
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(ClockRecyclerView.this,
                            CallAlarm.class);
                    PendingIntent sender = PendingIntent.getBroadcast(
                            ClockRecyclerView.this, 0, intent, 0);
                    AlarmManager am;
                    am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    am.cancel(sender);
                    Toast.makeText(ClockRecyclerView.this, "鬧钟已刪除！",
                            Toast.LENGTH_LONG).show();
                    tmpS="";
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
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

}
