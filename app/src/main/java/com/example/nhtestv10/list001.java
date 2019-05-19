package com.example.nhtestv10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class list001 extends AppCompatActivity {
    //ListView 要顯示的內容　改到全域變數
    Button BtnAdd = findViewById(R.id.add);
    Button BtnFix = findViewById(R.id.fix);
    Button BtnDelete = findViewById(R.id.delete);
    public String[] str = {"新北市","台北市","台中市","台南市","高雄市"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list001);
    }
}
