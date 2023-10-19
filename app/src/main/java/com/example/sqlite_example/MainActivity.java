package com.example.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<String > player_id,player_name,city;

    EditText e1,e2;
    Button b1,b2;
    DBHelper helper=new DBHelper(this);
    SQLiteDatabase db;
ListView list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editTextText);
        e2=findViewById(R.id.editTextText2);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

    }

    public void fetch_data(View view) {
        player_id=new ArrayList<String>();
        player_name=new ArrayList<String>();
        city=new ArrayList<>();
        db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from tab",null);
        int v=cursor.getCount();
        Toast.makeText(getApplicationContext(),"Total Data "+v,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),cursor.getCount(),Toast.LENGTH_SHORT).show();
        while(cursor.moveToNext()){
            player_id.add(cursor.getString(0));
            player_name.add(cursor.getString(1));
            city.add(cursor.getString(2));
        }
        Toast.makeText(getApplicationContext(),"ID "+player_id,Toast.LENGTH_SHORT).show();
        MyListAdapter adapter=new MyListAdapter(this, player_id, player_name,city);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    public void insert_data(View view) {
        String s1,s2;
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        Toast.makeText(getApplicationContext(),"Name=>"+s1+"\n City=>"+s2,Toast.LENGTH_SHORT).show();
        db=helper.getWritableDatabase();
        boolean rs=helper.insert_data(s1,s2,db);
        if (rs)
        {
            Toast.makeText(getApplicationContext(),"Done..",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"ny..",Toast.LENGTH_SHORT).show();
        }
    }


}