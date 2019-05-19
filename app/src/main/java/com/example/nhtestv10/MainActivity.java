package com.example.nhtestv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DisplayMetrics displayMetricss = getResources().getDisplayMetrics();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int vWidth = displayMetricss.widthPixels;
        int vHeight = displayMetricss.heightPixels;

        //FrameLayout ll = (FrameLayout)FrameLayout.(R.id.FrameLayout);
        //ll.getLayoutParams().width=(int)(vWidth*0.9);


        ImageButton ClockPageBtn = findViewById(R.id.clock);
        ImageButton ListPageBtn = findViewById(R.id.list);
        ImageButton CheckPageBtn = findViewById(R.id.check);
        ImageButton QRcodePageBtn = findViewById(R.id.QRcode);

        ClockPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , clock.class);
                startActivity(intent);
            }
        });
        ListPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , List.class);
                startActivity(intent);
            }
        });
        CheckPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , CheckIn.class);
                startActivity(intent);
            }
        });
        QRcodePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , QRcode.class);
                startActivity(intent);
            }
        });
    }

}
