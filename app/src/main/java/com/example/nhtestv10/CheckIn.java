package com.example.nhtestv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CheckIn extends AppCompatActivity {
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
                intent.setClass(CheckIn.this , MainActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        initDate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private List<CheckBean> checkBeanList;
    private MyAdapter mAdapter;
    private GridView mGridview;


    private void initDate() {

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int day = calendar.getActualMaximum(Calendar.DATE); // 获取当前月的天数

        checkBeanList = new ArrayList<CheckBean>();
        for (int i = 0; i < day + 1; i++) {
            CheckBean checkBean = new CheckBean();
            checkBean.day = i;
            checkBean.check_status = CheckBean.CHECK_NO;


            checkBeanList.add(checkBean);
        }

        mAdapter = new MyAdapter(CheckIn.this);
        mAdapter.setListDate(checkBeanList);

        mGridview = (GridView) findViewById(R.id.main_gridview);
        mGridview.setAdapter(mAdapter);
    }
}
