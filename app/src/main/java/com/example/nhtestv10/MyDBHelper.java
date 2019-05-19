package com.example.nhtestv10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    static String dbname = "mydb";
    static int version = 1;
    static String dbtableSQL = "CREATE TABLE mytable ( _id integer primary key not null,"
            + "name text not null,sex text,addr text)";

    // 內建的建構子，用來建立資料庫，但要傳入的參數有點多，所以我們改用自己的
    public MyDBHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    //我們自己的建構子只需傳入Context頁面即可
    public MyDBHelper(Context context) {
        super(context, dbname, null, version);
        // TODO Auto-generated constructor stub
    }
    //建立資料表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(dbtableSQL);

    }
    //更新資料表
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
