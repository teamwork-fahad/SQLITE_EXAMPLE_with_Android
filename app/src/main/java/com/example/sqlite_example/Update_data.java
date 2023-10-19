package com.example.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_data extends AppCompatActivity {
    String id;
   EditText e1,e2;
    String s1,s2;
   Button b1;
    DBHelper helper=new DBHelper(this);
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        id=getIntent().getExtras().getString("player_id");
        e1=findViewById(R.id.editTextText);
        e2=findViewById(R.id.editTextText2);
        b1=findViewById(R.id.button);
    }

    public void update_data(View view) {
        Toast.makeText(getApplicationContext(),"ID"+id,Toast.LENGTH_SHORT).show();
        s1=e1.getText().toString();
        s2=e2.getText().toString();
        db=helper.getWritableDatabase();
        helper.update_data(s1,s2,id,db);
    }
}