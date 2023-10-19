package com.example.sqlite_example;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context,"my_db2.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table tab(_id integer primary key autoincrement,player_name text,city_name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tab");
        onCreate(db);
    }

    public boolean insert_data(String s1, String s2,SQLiteDatabase db) {
        ContentValues cv=new ContentValues();
        cv.put("player_name",s1);
        cv.put("city_name",s2);
        long result=db.insert("tab",null,cv);

        if(result == -1) {
            return false;
        }else{
            //  Log.e(TAG,"value inserted");
            return true;
        }
    }


    public Cursor disp_data(SQLiteDatabase db) {
        Cursor cursor=null;
       cursor=db.rawQuery("select * from tab",null);
        return cursor;
    }

    public void update_data(String s1, String s2, String id, SQLiteDatabase db) {
        ContentValues cv=new ContentValues();
        cv.put("player_name",s1);
        cv.put("city_name",s2);
        long result=db.update("tab",cv,"_id="+id,null);
        db.close();

    }
}
