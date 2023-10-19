package com.example.sqlite_example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    ArrayList<String> pid;
        ArrayList<String> pname;
    ArrayList<String> city;
    DBHelper helper=new DBHelper(this.getContext());
    SQLiteDatabase db;
    public MyListAdapter(Context context, ArrayList<String> pid, ArrayList<String> pname, ArrayList<String> city) {
        super(context,R.layout.my_list,pname);
        this.context= (Activity) context;
        this.pid=pid;
        this.pname=pname;
        this.city=city;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        Button b1=(Button)rowView.findViewById(R.id.btnUpdate);
        Button b2=(Button)rowView.findViewById(R.id.btnDelete);
        String id=pid.get(position);
        titleText.setText(pname.get(position));
        subtitleText.setText(city.get(position));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MyListAdapter.this.getContext(),Update_data.class);
                i1.putExtra("player_id",id);
                context.startActivity(i1);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=helper.getReadableDatabase();
                long recremove = db.delete("tab", "_id=" + id, null);
                if (recremove != -1) {

                }
            }
        });

        return rowView;

    };
}
